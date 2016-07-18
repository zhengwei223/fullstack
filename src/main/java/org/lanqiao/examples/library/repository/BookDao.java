package org.lanqiao.examples.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.lanqiao.examples.library.domain.Book;
import org.lanqiao.examples.library.dto.BookDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * PagingAndSortingRepository默认有针对实体对象的CRUD与分页查询函数.
 * 
 */
@Repository
public interface BookDao extends PagingAndSortingRepository<BookDto, Long> {

	List<BookDto> findByOwnerId(@Param("ownerId") Long ownerId,
			@Param("pageable") Pageable pageable);

	List<BookDto> findByBorrowerId(Long borrowerId, Pageable pageable);

	List<BookDto> findAllBook(Pageable pageable);

	void updateBook(BookDto book);

	int findAllBookCount();
}
