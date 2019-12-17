package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.sampleInterface;
import com.example.demo.entity.FinalOrder;
import com.example.demo.entity.MyCollection;
import com.example.demo.service.IOrderService;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderServiceImpl;

	@Autowired
	private sampleInterface sampleInterface;

	/**
	 * Method : getOrderDetails()
	 * 
	 * Description : For getting past order details of the logged customer.
	 * 
	 * Parameters : cust_id (customer id information to be maintained till order
	 * submission)
	 * 
	 */

	@GetMapping(value = "/getOrderDetails/{cust_id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<FinalOrder> getOrderDetails(@PathVariable(value = "cust_id") int cust_id) {

		List<FinalOrder> ord = orderServiceImpl.getOrderDetails(cust_id);
		return ord;

	}

	/**
	 * Method : placeFinalOrder()
	 * 
	 * Description : For placing the final order. Here cart details will be
	 * retrieved and submitted as final order
	 * 
	 * Parameters : cust_id (customer id information to be maintained till order
	 * submission)
	 * 
	 */

	@RequestMapping(value = "/placeFinalOrder/{cust_id}", produces = "application/json")
	public ResponseEntity<String> placeFinalOrder(@PathVariable(value = "cust_id") int cust_id) {
		return orderServiceImpl.placeFinalOrder(cust_id);
	}

	/**
	 * Method : getAgeById()
	 * 
	 * Description : This method is not used as part of POC. This method was added
	 * to understand the usage and implementation of Mongo Repository
	 * 
	 * Parameters : age (List to be retrieved based on age)
	 * 
	 */

	@RequestMapping(value = "/getByAge/{age}")
	public MyCollection getAgeById(@PathVariable(value = "age") int age) {
		List<MyCollection> al = sampleInterface.findAll();
		MyCollection al1 = null;
		for (MyCollection myCollection : al) {
			if (myCollection.getAge() == age) {
				al1 = myCollection;
			}
		}

		return al1;
	}

	/**
	 * Method : getByAge()
	 * 
	 * Description : This method is not used as part of POC. This method was added
	 * to understand the usage and implementation of Mongo Repository
	 * 
	 * Parameters : No Parameters. This will show up all the details as response
	 * 
	 */

	@RequestMapping(value = "/getByAge")
	public List<MyCollection> getcollection() {
		return sampleInterface.findAll();
	}

	/**
	 * Method : addToCollection()
	 * 
	 * Description : This method is not used as part of POC. This method was added
	 * to understand the usage and implementation of Mongo Repository
	 * 
	 * Parameters : MyCollection object is being passed as parameter.
	 * 
	 */

	@RequestMapping(value = "/addToCollection")
	public List<MyCollection> addToCollection(MyCollection coll) {
		coll.setAge(26);
		coll.setName("Rama");
		coll.setLocation("Hyderabad");
		sampleInterface.save(coll);
		return sampleInterface.findAll();
	}
}
