package com.example.demo.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.orderInterface;
import com.example.demo.entity.FinalOrder;
import com.example.demo.entity.Product;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	orderInterface orderInterfaceImpl;

	private final MongoTemplate mongoTemplate;

	@Autowired
	public OrderServiceImpl(MongoTemplate mongoTemplate) {

		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public ResponseEntity<String> placeFinalOrder(int cust_id) {
		final String url = "http://localhost:9090/getCartDetails/{cust_id}";
		ResponseEntity<String> response;
		RestTemplate resttemplate = new RestTemplate();
		Product[] prod1 = resttemplate.getForObject(url, Product[].class, cust_id);
		FinalOrder finalorder = new FinalOrder();
		List<Product> prod2 = Arrays.asList(prod1);
		finalorder.setOrderAmount(finalorder.getTotalOrderPrice(prod2));
		finalorder.setCust_id(cust_id);
		finalorder.setFinalProductList(prod2);
		finalorder.setOrderDate(LocalDate.now());
		finalorder.setOrderStatus("Submitted");
		FinalOrder finalOrder = orderInterfaceImpl.save(finalorder);

		if (finalOrder != null) {
			response = new ResponseEntity<String>("Final order has been created successfully", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Order was not placed", HttpStatus.BAD_GATEWAY);
		}
		return response;
	}

	public List<FinalOrder> getOrderDetails(int cust_id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("cust_id").is(cust_id));
		return mongoTemplate.find(query, FinalOrder.class);
	}

}
