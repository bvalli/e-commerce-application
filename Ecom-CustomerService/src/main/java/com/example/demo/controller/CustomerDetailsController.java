package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pojo.Product;
import com.example.demo.service.ICustomerDetailsService;

@Controller

public class CustomerDetailsController {

	@Autowired
	private ICustomerDetailsService customerDetailsServiceImpl;

	/**
	 * Method: createCustomer() Description : Service will be called when any new
	 * customer opts to Register into application
	 */

	@RequestMapping("/createCustomer")

	public ResponseEntity<String> createCustomer(HttpServletRequest request) 
	{
		return customerDetailsServiceImpl.createCustomer(request);
	}

	@GetMapping("/welcome")
	public String getpage()
	{
		return "index";
	}

	@GetMapping("/registration")
	public String registration()
	{
		return "Registration";
	}

	/**
	 * Method: validateUser()
	 * 
	 * Description : Method will be called when user tries to login to the
	 * application. provided below are sample credentials to check the funtionality
	 */

	@RequestMapping("/login")
	public ResponseEntity<Product[]> validateUser(HttpServletRequest request)
	{
			return customerDetailsServiceImpl.validateUser(request);		
	}

	/**
	 * Method: addToCart()
	 * 
	 * Description : Service call for adding products to the cart. Here we have used
	 * restTemplate in order to provide interaction between two micro services.
	 */

	/*
	 * @RequestMapping("/addToCart")
	 * 
	 * private ResponseEntity<String> addToCart(HttpServletRequest request) { return
	 * customerDetailsServiceImpl.addToCart(request); }
	 */

}
