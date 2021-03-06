package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private int cartId;

	@OneToMany(mappedBy="cart",cascade = CascadeType.ALL)
	private List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}




	@OneToOne(cascade = CascadeType.ALL,targetEntity = CustomerEntity.class,optional = false)
	
	@JoinColumn(name="cust_id")
	private CustomerEntity customerEntity;

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
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
	public float getTotalOrderPrice(int quantity2, float price) {
		float sum = 0F;
		//Set<ProductDetailsEntity> cartProducts = getProdEntity();
		/*
		 * for (ProductDetailsEntity op : cartProducts) { sum +=
		 * (op.getProductPrice())*getQuantity(); }
		 */
		sum = price * quantity2;
		System.out.println("Sum" + sum);
		return sum;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	

}
