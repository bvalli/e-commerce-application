package com.example.demo.pojo;

public class Cart {

	
	 private int cartId;
	 
	
	 private int productId;
	 
	
	 private int quantity;
	 
	 private float cartPrice;


	


	public float getCartPrice() {
		return cartPrice;
	}


	public void setCartPrice(float price) {
		this.cartPrice = price;
	}


	



	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	


	public int getProductId() {
		return productId;
	}


	public void setProductId(int product_id) {
		this.productId = product_id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getTotalOrderPrice(int quantity,float price) {
		int sum = 0;
	     
	   
	         sum += price * quantity;
	     
	     System.out.println("Sum"+sum);
	     return sum;
	}


	

	
	 
	 
	 
	 

}
