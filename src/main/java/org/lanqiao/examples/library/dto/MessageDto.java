package org.lanqiao.examples.library.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MessageDto {

	public Long id;

	public String receiver;

	public String message;

	public Date receiveDate;

	
	public MessageDto(){
		
	}
	public MessageDto(Long id, String message,String receiver,
			Date receiveDate) {
		this.id = id;
		this.receiver = receiver;
		this.message = message;
		this.receiveDate = receiveDate;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
