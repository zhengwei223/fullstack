package org.lanqiao.examples.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javacommon.utils.Clock;
import javacommon.utils.Ids;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lanqiao.examples.library.domain.Book;
import org.lanqiao.examples.library.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于spring测试环境
@ContextConfiguration("classpath*:/spring/applicationContext-*.xml")//指定加载的Spring配置文件的位置
//@DirtiesContext //表明底层Spring容器在该方法的执行中被“污染”，从而必须在方法执行结束后重新创建（无论该测试是否通过）。
@ActiveProfiles("example-library")
public class BookDaoTest {

	@Autowired
	private BookDao bookDao;
	protected Clock clock = Clock.DEFAULT;
	@Test
	public void findByOwnerId() {
		List<BookDto> books = bookDao.findByOwnerId(1L, new PageRequest(0, 10));
		assertThat(books).hasSize(2);
		assertThat(books.get(0).title).isEqualTo("Big Data日知录");
	}

	@Test
	public void findByBorrowerId() {
		List<BookDto> books = bookDao.findByBorrowerId(1L, new PageRequest(0, 10));
		assertThat(books).hasSize(0);
	}
	@Test
	public void findAll(){
		Pageable pageable=new PageRequest(0,10);
		Iterable<BookDto> books=bookDao.findAllBook(pageable);
		assertThat(books).hasSize(6);
	}
	@Test
	public void findOne(){
		BookDto b=bookDao.findOne(2L);
		assertThat(b.doubanId).isEqualTo("25900156");
		
	}
	@Test
	public void saveBook(){
		BookDto b=new BookDto();
		b.id=Ids.randomLong();
		b.doubanId="44444";
		b.title="java编程思想4";
		b.url="http://book.douban.com/subject/25984046/";
		b.owner="1";
		b.onboardDate=clock.getCurrentDate();
		b.status = Book.STATUS_IDLE;
		b.borrower="2";
		b.borrowDate=clock.getCurrentDate();
		bookDao.save(b);
	}
	@Test
	public void modifyBook(){
		BookDto orginalBook=new BookDto();
		orginalBook.id=5122314507460121213L;
		orginalBook.title = "oracle数据库";
		orginalBook.url = "www.baidu.com";
		bookDao.updateBook(orginalBook);
	}
	@Test
	public void deleteBook(){
		bookDao.delete(5122314507460121213L);
	}
}
