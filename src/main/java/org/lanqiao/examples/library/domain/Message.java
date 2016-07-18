package org.lanqiao.examples.library.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javacommon.shiro.Account;

public class Message {

	public Long id;

	public Account receiver;

	public String message;

	public Date receiveDate;

	public Message() {

	}

	public Message(Account receiver, String message, Date receiveDate) {
		this.receiver = receiver;
		this.message = message;
		this.receiveDate = receiveDate;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
