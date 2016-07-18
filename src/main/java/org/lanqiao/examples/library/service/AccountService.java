package org.lanqiao.examples.library.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.lanqiao.examples.library.domain.Account;
import org.lanqiao.examples.library.repository.AccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import javacommon.shiro.ShiroDbRealm.ShiroUser;
import javacommon.shiro.ShiroUserService;
import javacommon.utils.Digests;
import javacommon.utils.Encodes;
import javacommon.utils.Ids;
import javacommon.web.ErrorCode;
import javacommon.web.ServiceException;

// Spring Bean的标识.
@Service("accountServcie_library")
public class AccountService implements ShiroUserService {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountDao accountDao;

	// 注入配置值
//	@Value("${app.loginTimeoutSecs:600}")
	private int loginTimeoutSecs=600;


	// guava cache
	private Cache<String, Account> loginUsers;

	@PostConstruct
	public void init() {
		loginUsers = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(loginTimeoutSecs, TimeUnit.SECONDS)
				.build();
	}

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

	public void logout(String token) {
		Account account = loginUsers.getIfPresent(token);
		if (account == null) {
			logger.warn("logout an alreay logout token:" + token);
		} else {
			loginUsers.invalidate(token);
		}
	}

	public Account getLoginUser(String token) {

		Account account = loginUsers.getIfPresent(token);

		if (account == null) {
			throw new ServiceException("User doesn't login", ErrorCode.UNAUTHORIZED);
		}

		return account;
	}

	@Transactional
	public void register(String email, String name, String password) {

		if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
			throw new ServiceException("Invalid parameter", ErrorCode.BAD_REQUEST);
		}

		Account account = new Account();
		account.email = email;
		account.name = name;
		account.hashPassword = hashPassword(password);
		accountDao.save(account);
	}

	protected static String hashPassword(String password) {
		return Encodes.encodeBase64(Digests.sha1(password));
	}
	/**
	 * 按登录名查询用户.
	 */
	public Account findAccountByLoginName(String loginName) {
		return accountDao.findByLoginName(loginName);
	}
	@Override
	public ShiroUser findByLoginName(String loginName) {
		Account account = findAccountByLoginName(loginName);
		return new ShiroUser(account.id, account.email, account.name);
	}
}
