package com.example.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.userCart;
import com.example.demo.exception.customerNotFoundException;
import com.example.demo.pojo.Product;

@Service
@Component
public interface ICustomerDetailsService{

	public ResponseEntity<String> createCustomer(HttpServletRequest request)  throws customerNotFoundException;
	
	public ResponseEntity<Product[]> validateUser(HttpServletRequest request) throws customerNotFoundException;
	
	public ResponseEntity<String> addToCart(HttpServletRequest request) throws customerNotFoundException;
}
