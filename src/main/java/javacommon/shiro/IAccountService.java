package javacommon.shiro;

import java.util.List;

public interface IAccountService {
	String HASH_ALGORITHM = "SHA-1";
	int HASH_INTERATIONS = 1024;
	int SALT_SIZE = 8;
	
	String login(String email, String password);

	void logout(String token);

	Account getLoginUser(String token);

	void register(Account account);

	Account findAccountByLoginName(String loginName);
	public List<Account> getAllAccount();
	public Account getAccount(Long id);
	public void updateAccount(Account account);
	public void deleteAccount(Long id);

}