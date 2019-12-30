package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Product;

public interface ProductService {
	
	public List<Product> getCartdetails(int cust_id);
	public ResponseEntity<String> addToCart();
	public List<Product> getProductDetailsByProductId(int productId);
	public  List<Product> getProductDetails();
	public List<Product> removeFromCart(int id);


}
