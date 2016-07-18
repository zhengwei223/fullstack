package org.lanqiao.examples.library.api;

import java.util.Collections;
import java.util.Map;

import org.lanqiao.examples.library.domain.Account;
import org.lanqiao.examples.library.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javacommon.constants.MediaTypes;
import javacommon.shiro.ShiroUserService;
import javacommon.web.ErrorCode;
import javacommon.web.ServiceException;

// Spring Restful MVC Controller的标识, 直接输出内容，不调用template引擎.
@RestController
public class AccountEndPoint {

	private static Logger logger = LoggerFactory.getLogger(AccountEndPoint.class);

	@Autowired
	@Qualifier("accountServcie_library")
	private AccountService accountServcie;

	@RequestMapping(value = "/api/accounts/login", produces = MediaTypes.JSON_UTF_8)
	public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or password empty", ErrorCode.BAD_REQUEST);
		}

		String token = accountServcie.login(email, password);

		return Collections.singletonMap("token", token);
	}

	@RequestMapping(value = "/api/accounts/logout")
	public void logout(@RequestParam(value = "token", required = false) String token) {
		accountServcie.logout(token);
	}

	@RequestMapping(value = "/api/accounts/register")
	public void register(@RequestParam("email") String email,
			@RequestParam(value = "name", required = false) String name, @RequestParam("password") String password) {

		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
			throw new ServiceException("User or name or password empty", ErrorCode.BAD_REQUEST);
		}

		accountServcie.register(email, name, password);
	}
}
