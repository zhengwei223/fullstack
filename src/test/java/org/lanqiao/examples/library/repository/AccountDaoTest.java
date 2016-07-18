package org.lanqiao.examples.library.repository;

import static org.assertj.core.api.Assertions.assertThat;
import javacommon.utils.Digests;
import javacommon.utils.Encodes;
import javacommon.utils.Ids;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.examples.library.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于spring测试环境
@ContextConfiguration("classpath*:/spring/applicationContext-*.xml")//指定加载的Spring配置文件的位置
//@DirtiesContext //表明底层Spring容器在该方法的执行中被“污染”，从而必须在方法执行结束后重新创建（无论该测试是否通过）。
@ActiveProfiles("example-library")
public class AccountDaoTest {

	@Autowired
	private AccountDao accountDao;
	@Test
	public void findByEmail(){
		Account a= accountDao.findByEmail("calvin.xiao@springside.io");
		assertThat(a.id).isEqualTo(1L);
	}
	@Test
	public void findByLoginName(){
		Account a=accountDao.findByLoginName("David");
		assertThat(a.id).isEqualTo(2L);
	}
	@Test
	public void saveUser(){
		Account account = new Account();
		account.id=Ids.randomLong();
		account.email = "1278006075@qq.com";
		account.name = "丁彦涛";
		account.hashPassword =Encodes.encodeBase64(Digests.sha1("2551155"));
		accountDao.save(account);
	}

}
