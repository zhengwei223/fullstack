package org.lanqiao.examples.library.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookDto {

	public Long id;
	public String bookId;
	public String title;
	public String url;
	public String status;

	public String doubanId;


	public String owner;
	public String borrower;


	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date onboardDate;


	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public Date borrowDate;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
