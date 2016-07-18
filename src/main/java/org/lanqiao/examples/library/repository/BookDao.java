package org.lanqiao.examples.library.repository;

import java.util.List;

import org.lanqiao.examples.library.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 
 * PagingAndSortingRepository默认有针对实体对象的CRUD与分页查询函数.
 * 
 */
public interface BookDao extends PagingAndSortingRepository<Book, Long> {

	List<Book> findByOwnerId(Long ownerId, Pageable pageable);

	List<Book> findByBorrowerId(Long borrowerId, Pageable pageable);
}
