package org.lanqiao.examples.library.service;

import java.util.List;

import org.lanqiao.examples.library.domain.Account;
import org.lanqiao.examples.library.domain.Book;
import org.lanqiao.examples.library.dto.BookDto;
import org.lanqiao.examples.library.repository.BookDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javacommon.utils.Clock;
import javacommon.utils.Ids;
import javacommon.web.ErrorCode;
import javacommon.web.ServiceException;

// Spring Bean的标识.
@Service
public class BookAdminService {

	private static Logger logger = LoggerFactory.getLogger(BookBorrowService.class);

	@Autowired
	private BookDao bookDao;

	// 可注入的Clock，方便测试时控制日期
	protected Clock clock = Clock.DEFAULT;

	@Transactional(readOnly = true)
	public List<BookDto> findAll(Pageable pageable) {
		return bookDao.findAllBook(pageable);
	}

	@Transactional(readOnly = true)
	public BookDto findOne(Long id) {
		return bookDao.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<BookDto> listMyBook(Long ownerId, Pageable pageable) {
		return bookDao.findByOwnerId(ownerId, pageable);
	}

	@Transactional
	public void saveBook(BookDto book) {
		book.id=Ids.randomLong();
		book.status = Book.STATUS_IDLE;
		book.onboardDate = clock.getCurrentDate();

		bookDao.save(book);
	}

	@Transactional
	public void modifyBook(Book book, Long currentAccountId) {
		if (!currentAccountId.equals(book.owner.id)) {
			logger.error("user:" + currentAccountId + " try to modified a book:" + book.id + " which is not him");
			throw new ServiceException("User can't modify others book", ErrorCode.BOOK_OWNERSHIP_WRONG);
		}

		BookDto orginalBook = bookDao.findOne(book.id);

		if (orginalBook == null) {
			logger.error("user:" + currentAccountId + " try to modified a book:" + book.id + " which is not exist");
			throw new ServiceException("The Book is not exist", ErrorCode.BAD_REQUEST);
		}

		orginalBook.title = book.title;
		orginalBook.url = book.url;
		bookDao.updateBook(orginalBook);
	}

	@Transactional
	public void deleteBook(Long id, Long currentAccountId) {
		BookDto book = bookDao.findOne(id);

		if (book == null) {
			logger.error("user:" + currentAccountId + " try to delete a book:" + id + " which is not exist");
			throw new ServiceException("The Book is not exist", ErrorCode.BAD_REQUEST);
		}

		if (!currentAccountId.equals(book.owner)) {
			logger.error("user:" + currentAccountId + " try to delete a book:" + book.id + " which is not him");
			throw new ServiceException("User can't delete others book", ErrorCode.BOOK_OWNERSHIP_WRONG);
		}

		bookDao.delete(id);
	}

	public int findAllBookCount() {
		return bookDao.findAllBookCount();
	}
}
