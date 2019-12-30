package com.example.demo.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductInterface;
import com.example.demo.repository.cartInterface;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ProductsController {

	@Autowired
	private ProductService productServiceImpl;

	@Autowired
	private ProductInterface productInterface;

	@Autowired
	private cartInterface cartInterface;

	/**
	 * Method: getProductDetails()
	 * 
	 * Description : Method to be utilized for getting all the products available in
	 * the store
	 */

	@GetMapping(value = "/getProductDetails", produces = "application/json")

	public List<Product> getProductDetails() throws JsonProcessingException {
		return productServiceImpl.getProductDetails();

	}

	/**
	 * Method: getProductDetailsByProductId()
	 * 
	 * Description : Method to be utilized for to view particular product details
	 * 
	 * Parameters : Product Id
	 */

	@GetMapping("/productDetailsByProductId/{product_id}")

	public List<Product> getProductDetailsByProductId(@PathVariable(name = "product_id") int productId) {
		return productServiceImpl.getProductDetailsByProductId(productId);

	}

	/**
	 * Method: addToCart()
	 * 
	 * Description : Method for adding products to the ecart.
	 */

	@RequestMapping("/addToCart")

	public ResponseEntity<String> addToCart() {
		return productServiceImpl.addToCart();

	}

	/**
	 * Method: getCartdetails()
	 * 
	 * Description : Method to retrieve products added to the cart
	 * 
	 * Parameters : customer id will be passed as parameter which is tracked through
	 * session
	 */

	@GetMapping(value = "/getCartDetails/{cust_id}", produces = "application/json")

	public List<Product> getCartdetails(@PathVariable(name = "cust_id") int cust_id)
			throws JsonProcessingException, JSONException {
		return productServiceImpl.getCartdetails(cust_id);

	}

	/**
	 * Method: removeFromCart()
	 * 
	 * Description : Method to remove product from the cart
	 * 
	 * Parameters : Product id will be passed as parameter which is tracked through
	 * session
	 */

	@RequestMapping("/removeFromCart/{id}")

	public List<Product> removeFromCart(@PathVariable(value = "id") int id) {

		
		return productServiceImpl.removeFromCart(id);

	}

}
