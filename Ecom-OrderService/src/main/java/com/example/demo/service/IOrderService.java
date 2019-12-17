package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.FinalOrder;

public interface IOrderService {

	public ResponseEntity<String> placeFinalOrder(int cust_id);

	public List<FinalOrder> getOrderDetails(int cust_id);
}
