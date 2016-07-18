package org.lanqiao.examples.library.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javacommon.shiro.Account;

public class Book {

	public static final String STATUS_IDLE = "idle";
	public static final String STATUS_REQUEST = "request";
	public static final String STATUS_OUT = "out";

	public Long id;

	public String doubanId;

	public String title;

	public String url;

	public String status;

	public Account owner;

	public Date onboardDate;

	public Account borrower;

	public Date borrowDate;

	
	public Book() {
	}

	public Book(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
