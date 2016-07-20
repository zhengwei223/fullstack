package javacommon.web;

import org.springframework.data.domain.Pageable;

/**
 * 封装集合，提供分页JSON的包装类
 * 
 * @todo
 * @author 丁彦涛
 * @date 2016年7月19日
 */
public class PAGEJSON {

	public Pageable pageable;
	public Object data;

	public PAGEJSON(Pageable pageable, Object data) {
		super();
		this.pageable = pageable;
		this.data = data;
	}
}
