/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.lanqiao.examples.task.repository;

import org.lanqiao.examples.task.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskDao extends PagingAndSortingRepository<Task, Long>{

	Page<Task> findByUserId(Long id, Pageable pageRequest);

//	@Query("delete from Task task where task.user.id=?1")
	void deleteByUserId(Long id);
}
