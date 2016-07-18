package org.lanqiao.examples.library.repository;

import org.lanqiao.examples.library.domain.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 */
public interface MessageDao extends CrudRepository<Message, Long> {
}
