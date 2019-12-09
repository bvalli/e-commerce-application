package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.orderInterface;
import com.example.demo.entity.FinalOrder;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	orderInterface orderInterfaceImpl;
	
	
	
	/*
	 * @Override public ResponseEntity<String> createOrder() {
	 * 
	 * System.out.println("inside create order Method"); Orders ord= new Orders();
	 * Set<Product> productList= new HashSet<Product>(); Product
	 * prod1=Product.newProduct("1"); productList.add(prod1); Product
	 * prod2=Product.newProduct("33"); productList.add(prod2);
	 * 
	 * ord.setProductList(productList); ord.setOrderDate(LocalDate.now());
	 * ord.setStatus("Ordered");
	 * 
	 * 
	 * Orders ordd= orderInterfaceImpl.save(ord); if(ordd != null) { return new
	 * ResponseEntity<String>("Order was created successfully.. Order Id : "+ord.
	 * getId()+"for tracking purpose", HttpStatus.ACCEPTED); } else { return new
	 * ResponseEntity<>("There is some problem with creating the order ",HttpStatus.
	 * NOT_ACCEPTABLE); }
	 * 
	 * }
	 */

	
	 
	 

	@Override
	public ResponseEntity<String> placeFinalOrder(int cust_id) {
		final String url = "http://localhost:9090/getCartDetails/{cust_id}";
		ResponseEntity<String> response;
		RestTemplate resttemplate = new RestTemplate();
		Product[] prod1=resttemplate.getForObject(url, Product[].class, cust_id);
		FinalOrder finalorder= new FinalOrder();
		List<Product> prod2=Arrays.asList(prod1);
		finalorder.setOrderAmount(finalorder.getTotalOrderPrice(prod2));
		finalorder.setCust_id(cust_id);
		finalorder.setFinalProductList(prod2);
		finalorder.setOrderDate(LocalDate.now());
		finalorder.setOrderStatus("Submitted");
		FinalOrder finalOrder= orderInterfaceImpl.save(finalorder);
	
		
		if(finalOrder !=  null)
		{
			response= new ResponseEntity<String>("Final order has been created successfully",HttpStatus.OK);
		}
		else
		{
			response = new ResponseEntity<String>("Order was not placed",HttpStatus.BAD_GATEWAY);
		}
		return response;
	}



	public List<FinalOrder> getOrderDetails(int cust_id) {
		// TODO Auto-generated method stub
		return orderInterfaceImpl.findAllById(cust_id);
	}
	/*
	 * public List<FinalOrder> getOrderProducts(int cust_id){ return
	 * orderInterfaceImpl.findAllProductsById(cust_id); }
	 */
	
	}


