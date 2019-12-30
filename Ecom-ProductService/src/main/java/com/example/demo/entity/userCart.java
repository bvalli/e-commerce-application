package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "cart_details")
public class userCart implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@Column(name = "id",nullable = false)
	private int id;

	@OneToMany(mappedBy="cart",cascade = CascadeType.PERSIST)
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	//@OneToOne(cascade = CascadeType.ALL, mappedBy="cart")
	@OneToOne(cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name="cust_id")
	
	private CustomerEntity customerEntity;

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	

	public userCart() {
		
	}


	@Column(name = "quantity")
	private int quantity;

	@Column(name = "total")
	private float cartPrice;
	

	public float getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(float cartPrice) {
		this.cartPrice = cartPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Transient
	public void getTotalOrderPrice() {
		float sum = 0F;
		List<Product> cartProducts = getProductList();
		cartProducts.stream().forEach((Product) -> {Product.getCart().setCartPrice((Product.getProductPrice() * Product.getQuantity()));     });
		//return sum;
		
		
		  
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

}
