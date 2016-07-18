package org.lanqiao.examples.library.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Account {

	public Long id;

	public String email;
	public String name;
	public String hashPassword;

	public Account() {

	}

	public Account(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
