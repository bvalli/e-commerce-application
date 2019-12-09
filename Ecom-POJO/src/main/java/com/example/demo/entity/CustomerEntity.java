package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.example.demo.pojo.Cart;

@Entity
@Table(name="customer")
public class CustomerEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cust_id")
	
	private int cust_id;
	@Column(name="name")
	private String custName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="email_Id")
	private String emailId;
	
	@Column(name="Mobile")
	private String mobileNumber;
	
	@Column(name="Zipcode")
	private String zipcode;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL,optional = false)
	@PrimaryKeyJoinColumn
	private userCart cartdet;
	
	
	public CustomerEntity() {
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "CustomerEntity [custName=" + custName + ", gender=" + gender + ", emailId=" + emailId
				+ ", mobileNumber=" + mobileNumber + ", zipcode=" + zipcode + "]";
	}
	public userCart getCartdet() {
		return cartdet;
	}
	public void setCartdet(userCart cartdet) {
		this.cartdet = cartdet;
	}
	public CustomerEntity(String custName, String gender, String emailId, String mobileNumber, String zipcode,
			String password) {
		
		this.custName = custName;
		this.gender = gender;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.zipcode = zipcode;
		this.password = password;
	}
	public static CustomerEntity newCustomerEntity(String id) {
		CustomerEntity customer =new CustomerEntity("Valli"+id, "Female", "valli@gmail.com"+id, "9010136632", "534211", "valli@123");
		
		return customer;
	}
	
}
