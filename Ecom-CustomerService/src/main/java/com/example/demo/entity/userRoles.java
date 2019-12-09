package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class userRoles implements Serializable {
	
	@Id 
	@Column(name="mobile",nullable = false,unique = true)
	private int mobile;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="roles",nullable = false)
	private String role;

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
