package org.lanqiao.examples.library.repository;

import org.springframework.data.repository.CrudRepository;

import javacommon.shiro.Account;

/**
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 */
public interface AccountDao extends CrudRepository<Account, Long> {

	Account findByEmail(String email);
}
