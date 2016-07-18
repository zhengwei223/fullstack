package javacommon.shiro;

import javacommon.shiro.ShiroDbRealm.ShiroUser;

public interface ShiroUserService {
	String HASH_ALGORITHM = "SHA-1";
	int HASH_INTERATIONS = 1024;
	int SALT_SIZE = 8;

	ShiroUser findByLoginName(String loginName);

}