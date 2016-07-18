package org.lanqiao.examples.library.service;

import org.junit.Test;
import org.lanqiao.examples.library.service.AccountService;

public class AccountServiceTest {

	@Test
	public void hash() throws Exception {
		System.out.println("hashPassword:" + AccountService.hashPassword("springside"));
	}

}
