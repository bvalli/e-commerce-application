package com.example.demo.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.FinalOrder;
import com.example.demo.service.IOrderService;

@RestController
public class OrderController {
	
	@Autowired
	private IOrderService orderServiceImpl;
	
	
	  @GetMapping(value="/getOrderDetails",produces =org.springframework.http.MediaType.APPLICATION_JSON_VALUE) 
	  public List<FinalOrder> getOrderDetails()
	  {
		  int cust_id=155;
	  List<FinalOrder> ord= orderServiceImpl.getOrderDetails(cust_id);
	 // List<FinalOrder> pro=orderServiceImpl.getOrderProducts(cust_id);
	
	  List<FinalOrder> c= ord.stream().filter(Objects::nonNull)
			  .distinct()
			  .collect(Collectors.toList());
	  
		System.out.println(c);			  
	  return c;
	  
	  }
	  
	/*
	 * @RequestMapping("/createOrder") public ResponseEntity<String> createOrder() {
	 * return orderServiceImpl.createOrder(); }
	 * 
	 */
int cust_id;
@RequestMapping(value="/placeFinalOrder/{cust_id}",produces = "application/json")
public ResponseEntity<String> placeFinalOrder(@PathVariable(value="cust_id") int cust_id)
{
	return orderServiceImpl.placeFinalOrder(cust_id);
}

}
