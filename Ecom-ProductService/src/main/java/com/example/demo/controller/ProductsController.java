package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Controller;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.userCart;
import com.example.demo.repository.ProductInterface;
import com.example.demo.repository.cartInterface;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;


@RestController
public class ProductsController {
	
	@Autowired
	
	private ProductService productServiceImpl;
	
	@Autowired
	private cartInterface cartInterface;

	
	@GetMapping(value="/getProductDetails",produces = "application/json") 
	
	public  List<Product> getProductDetails() throws JsonProcessingException
	{
		return productServiceImpl.getProductDetails();

	}
	
	@GetMapping("/productDetailsByProductId/{product_id}")
	public List<Product> getProductDetailsByProductId(@PathVariable(name="product_id") int productId)
	{
		return productServiceImpl.getProductDetailsByProductId(productId);
		
		
	}
	
	
	/*
	 * Method: addToCart() 
	 * 
	 * Description : Method for adding products to the ecart.
	 */
	
	
	@RequestMapping("/addToCart")
	public ResponseEntity<String> addToCart()
	{
		return productServiceImpl.addToCart();
		
	}
	
	@GetMapping(value="/getCartDetails/{cust_id}",produces = "application/json")
	
	public List<Product> getCartdetails(@PathVariable(name="cust_id") int cust_id) throws JsonProcessingException, JSONException
	{
		return productServiceImpl.getCartdetails(cust_id);
	
				 
	}
		
		
		
	
}
