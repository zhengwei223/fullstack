package org.lanqiao.examples.library.repository;

import org.lanqiao.examples.library.domain.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 */
public interface AccountDao extends CrudRepository<Account, Long> {

	Account findByEmail(String email);

	Account findByLoginName(String loginName);
}
