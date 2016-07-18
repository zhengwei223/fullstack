package org.lanqiao.examples.library.repository;

import org.lanqiao.examples.library.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * CrudRepository默认有针对实体对象的CRUD方法.
 * 
 */
@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

	Account findByEmail(String email);

	Account findByLoginName(String loginName);
}
