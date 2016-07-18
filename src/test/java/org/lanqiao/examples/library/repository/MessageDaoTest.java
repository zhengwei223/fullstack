package org.lanqiao.examples.library.repository;

import static org.assertj.core.api.Assertions.assertThat;
import javacommon.utils.Clock;
import javacommon.utils.Ids;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.examples.library.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于spring测试环境
@ContextConfiguration("classpath*:/spring/applicationContext-*.xml")//指定加载的Spring配置文件的位置
//@DirtiesContext //表明底层Spring容器在该方法的执行中被“污染”，从而必须在方法执行结束后重新创建（无论该测试是否通过）。
@ActiveProfiles("example-library")
public class MessageDaoTest {

	@Autowired
	private MessageDao messageDao;
	protected Clock clock = Clock.DEFAULT;
	@Test
	public void saveMessage() {
		MessageDto message = new MessageDto(Ids.randomLong(),
				String.format("Apply book <%s> request by %s", "DSL实战", "Div"),"1" ,clock.getCurrentDate());
		MessageDto m=messageDao.save(message);
		assertThat(m.receiver).isEqualTo("1");
	}

}
