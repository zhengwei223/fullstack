package org.lanqiao.examples.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.examples.library.domain.Book;
import org.lanqiao.examples.library.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于spring测试环境
@ContextConfiguration("classpath*:/spring/applicationContext-*.xml")//指定加载的Spring配置文件的位置
//@DirtiesContext //表明底层Spring容器在该方法的执行中被“污染”，从而必须在方法执行结束后重新创建（无论该测试是否通过）。
public class BookDaoTest {

	@Autowired
	private BookDao bookDao;

	@Test
	public void findByOwnerId() {
		List<Book> books = bookDao.findByOwnerId(1L, new PageRequest(0, 10));
		assertThat(books).hasSize(2);
		assertThat(books.get(0).title).isEqualTo("Big Data日知录");
	}

	@Test
	public void findByBorrowerId() {
		List<Book> books = bookDao.findByBorrowerId(1L, new PageRequest(0, 10));
		assertThat(books).hasSize(0);
	}
}
