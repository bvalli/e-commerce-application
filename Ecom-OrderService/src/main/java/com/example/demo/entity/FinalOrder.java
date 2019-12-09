package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table

public class FinalOrder {
	
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@Column(name="orderDate")
	private LocalDate orderDate;
	
	@Column(name="orderAmount")
	private float orderAmount;
	
	@Column(name="orderStatus")
	private String orderStatus;
	
	@Column(name="productList")
	@ElementCollection
	@JsonProperty("Product")
	@JsonIgnore
	private List<Product> finalProductList;	
	
	@Column(name="custId")
	private int cust_id;
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public float getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(float orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	

	public List<Product> getFinalProductList() {
		return finalProductList;
	}

	public void setFinalProductList(List<Product> finalProductList) {
		this.finalProductList = finalProductList;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	@Transient
	public float getTotalOrderPrice(List<Product> prod) {
		float sum = 0F;
		//List<Product> cartProducts = prod;
		
		  for (Product op : prod)
		  {
			  sum += (op.getProductPrice())* (op.getQuantity()); 
			  
		  }
		 
	//	sum = price * quantity2;
		System.out.println("Sum" + sum);
		return sum;
	}
	
	
}
