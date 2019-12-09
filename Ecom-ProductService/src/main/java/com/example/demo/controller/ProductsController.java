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
	
	private ProductInterface productInterface;
	
	@Autowired
	private cartInterface cartInterface;

	
	@GetMapping(value="/getProductDetails",produces = "application/json") 
	
	public  String getProductDetails() throws JsonProcessingException
	{
		System.out.println("inside getProductDetails");
		List<Product> productData= (List<Product>) productInterface.findAll();
		ObjectMapper objectMapper = new ObjectMapper();
		String productDataAsString = objectMapper.writeValueAsString(productData.toString());
		if(productDataAsString !=null)
		{
			return productDataAsString;
		}
		return productDataAsString;

	}
	
	@GetMapping("/productDetailsByProductId/{product_id}")
	public List<Product> getProductDetailsByProductId(@PathVariable(name="product_id") int productId)
	{
		
		List<Product> cartdet=productInterface.getProductDetailsByProdId(productId);
		/*
		 * System.out.println(cartdet); ObjectMapper objectMapper = new ObjectMapper();
		 * String productDataAsString = null; try { productDataAsString =
		 * objectMapper.writeValueAsString(cartdet.toString()); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } if(productDataAsString !=null) { return
		 * productDataAsString; } return productDataAsString;
		 */
		return cartdet;
	}
	
	
	/*
	 * Method: addToCart() 
	 * 
	 * Description : Method for adding products to the ecart.
	 */
	
	
	@RequestMapping("/addToCart")
	public ResponseEntity<String> addToCart()
	{
		System.out.println("inside add to cart");
		userCart userc=new userCart();
		List<Product> productList= new ArrayList<Product>();
		Product prod1=Product.newProduct("1");
		productList.add(prod1);
		userc.setProductList(productList);
		CustomerEntity customerEntity1 = CustomerEntity.newCustomerEntity("1");
		Orders ordersEntity= new Orders();
		ordersEntity.setOrderDate(LocalDate.now());
		ordersEntity.setStatus("In Cart");
		userc.setCustomerEntity(customerEntity1);
	
		prod1.setCart(userc);
		prod1.setOrders(ordersEntity);
		Product prod2=Product.newProduct("2");	
		productList.add(prod2);
		userc.setCustomerEntity(customerEntity1);
		userc.setProductList(productList);
		userc.setCartPrice(userc.getTotalOrderPrice());
		//userc.setId(cartInterface.findcartbyId(cust_id));
		
		prod2.setCart(userc);

		prod2.setOrders(ordersEntity);
		
		userc.setCartPrice(userc.getTotalOrderPrice());
	
	
		Product cartout= productInterface.save(prod1);
		Product cartout1= productInterface.save(prod2);
			if(cartout != null && cartout1 != null )
		{
		 return new ResponseEntity<String>("Product added to cart successfully", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("Product could not be added to cart ",HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@GetMapping(value="/getCartDetails/{cust_id}",produces = "application/json")
	
	public @ResponseBody  List<Product> getCartdetails(@PathVariable(name="cust_id") int cust_id) throws JsonProcessingException, JSONException
	{
		List<Product> cart= productInterface.findCartDetailsByCustId(cust_id);
	//	ObjectMapper objectMapper = new ObjectMapper();
	//	objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	// objectMapper.writeValue(null, cart);
	//	System.out.println(cartAsString);
		return cart;
		 

				 
	}
		
		
		
	
}
