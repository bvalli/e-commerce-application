package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FinalOrder;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;


public interface IOrderService{
	
	/*
	 * public ResponseEntity<String> createOrder();
	 * 
	 * public List<Orders> getOrderDetails();
	 */
	public ResponseEntity<String> placeFinalOrder(int cust_id);

	//public List<FinalOrder> getOrderProducts(int cust_id);

	public List<FinalOrder> getOrderDetails(int cust_id);
}
