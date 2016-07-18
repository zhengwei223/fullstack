package org.lanqiao.examples.library.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.lanqiao.examples.library.repository.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javacommon.shiro.Account;
import javacommon.shiro.IAccountService;
import javacommon.utils.Digests;
import javacommon.utils.Encodes;
import javacommon.utils.Ids;
import javacommon.web.ErrorCode;
import javacommon.web.ServiceException;

// Spring Bean的标识.
@Service("accountServcie")
public class AccountService implements IAccountService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountDao accountDao;

	// 注入配置值
	@Value("${app.loginTimeoutSecs:600}")
	private int loginTimeoutSecs;

	// guava cache
	private Cache<String, Account> loginUsers;

	@PostConstruct
	public void init() {
		loginUsers = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
				.build();
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.examples.library.service.IAccountService#login(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public String login(String email, String password) {
		Account account = accountDao.findByEmail(email);

		if (account == null) {
			throw new ServiceException("User not exist", ErrorCode.UNAUTHORIZED);
		}

		if (!account.hashPassword.equals(hashPassword(password))) {
			throw new ServiceException("Password wrong", ErrorCode.UNAUTHORIZED);
		}

		String token = Ids.uuid2();
		loginUsers.put(token, account);
		return token;
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.examples.library.service.IAccountService#logout(java.lang.String)
	 */
	@Override
	public void logout(String token) {
		Account account = loginUsers.getIfPresent(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			loginUsers.invalidate(token);
		}
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.examples.library.service.IAccountService#getLoginUser(java.lang.String)
	 */
	@Override
	public Account getLoginUser(String token) {

		Account account = loginUsers.getIfPresent(token);

		if (account == null) {
			throw new ServiceException("User doesn't login", ErrorCode.UNAUTHORIZED);
		}

		return account;
	}

	/* (non-Javadoc)
	 * @see org.lanqiao.examples.library.service.IAccountService#register(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void register(Account account) {
		account.hashPassword = hashPassword(account.getPassword());
		accountDao.save(account);
	}

	protected static String hashPassword(String password) {
		return Encodes.encodeBase64(Digests.sha1(password));
	}

	@Override
	public Account findAccountByLoginName(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccount(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		
	}
}
