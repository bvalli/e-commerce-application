package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")
public class Product implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native")
	@Column(name="product_id")
	private int id;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_description")
	private String productDescription;
	
	@Column(name="product_price")
	private float productPrice;
	
	@Column(name="product_quantity")
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name="cart_id",referencedColumnName = "cart_id")
	@NotFound(action = NotFoundAction.IGNORE)

	private userCart cart;
	
	@ManyToOne(cascade = CascadeType.ALL,optional = false)
	@JoinColumn(name="id",referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)

	@JsonIgnore
	private Orders orders;
	
	
	
	public userCart getCart() {
		return cart;
	}

	public void setCart(userCart cart) {
		this.cart = cart;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	

	public Product()
	{
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	/*
	 * @Override public String toString() { return "Product [productName=" +
	 * productName + ", productDescription=" + productDescription +
	 * ", productPrice=" + productPrice + ", quantity=" + quantity + ", cart=" +
	 * cart + ", orders=" + orders + "]"; }
	 */


	

	public Product(String productName, String productDescription, float productPrice, int quantity) {
		super();
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productDescription=" + productDescription + ", productPrice="
				+ productPrice + ", quantity=" + quantity + "]";
	}

	public static Product newProduct(String id) {
		Product prod =new Product("Wallet"+id, "Ladies Wallet"+id, 450, 1);
		
		return prod;
	}
	

}
