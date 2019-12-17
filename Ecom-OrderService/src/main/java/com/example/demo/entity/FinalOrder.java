package com.example.demo.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FinalOrder {

	@Id
	private int Id;
	private LocalDate orderDate;
	private float orderAmount;
	private String orderStatus;

	private List<Product> finalProductList;

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

	public float getTotalOrderPrice(List<Product> prod) {
		float sum = 0F;
		// List<Product> cartProducts = prod;

		for (Product op : prod) {
			sum += (op.getProductPrice()) * (op.getQuantity());

		}

		// sum = price * quantity2;
		System.out.println("Sum" + sum);
		return sum;
	}

}
