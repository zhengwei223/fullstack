package javacommon.shiro;


import java.beans.Transient;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;

public class Account {
	public Long id;
	private String loginName;
	public String name;
	public String password;
	public String hashPassword;
	private String salt;
	private String roles;
	public Account() {

	}
	public Account(String email, String name, String password) {
		this.loginName = email;
		this.name = name;
		this.password = password;
	}
	public Account(long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHashPassword() {
		return hashPassword;
	}


	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Transient
	@JsonIgnore
	public List<String> getRoleList() {
		// 角色列表在数据库中实际以逗号分隔字符串存储，因此返回不能修改的List.
		return ImmutableList.copyOf(StringUtils.split(roles, ","));
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
