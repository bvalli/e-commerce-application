package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.CustomerDetailsDAO;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.userCart;
import com.example.demo.exception.customerNotFoundException;
import com.example.demo.pojo.Cart;
import com.example.demo.pojo.Product;
import java.util.logging.Logger;

@Service
public class CustomerDetailsServiceImpl implements ICustomerDetailsService{

	@Autowired
	CustomerDetailsDAO customerDetailsDAO;
	protected Logger logger = Logger.getLogger(CustomerDetailsServiceImpl.class.getName());
	
	@Override
	public ResponseEntity<String> createCustomer(HttpServletRequest request) throws customerNotFoundException
	{
		
		logger.info("createCustomer() invoked:");

		CustomerEntity customerEntity1 = CustomerEntity.newCustomerEntity("1");
		
		userCart cart = new userCart();
		cart.setCustomerEntity(customerEntity1);
	
		try {
			cart = customerDetailsDAO.save(cart);
			logger.info("Customer Information saved successfully. Registered Mobile Number :  "+cart.getCustomerEntity().getMobileNumber());
		} catch (customerNotFoundException e) {
			throw new customerNotFoundException("Unable to create customer account");
		}
		
		if (cart != null) {
			
			return new ResponseEntity<String>("Customer Information saved successfully. Registered Mobile Number :"+cart.getCustomerEntity().getMobileNumber()+" ", HttpStatus.OK);
		} 
		else {
			
			return new ResponseEntity<String>("Problem with customer account creation here",
					HttpStatus.EXPECTATION_FAILED);

		}
	}

	@Override
	public ResponseEntity<Product[]> validateUser(HttpServletRequest request) throws customerNotFoundException
	{
		String mobileNumber = "9949391972";
		String password = "raja@123";
		List<CustomerEntity> login;
		
		ResponseEntity<Product[]> response = null ;
		try {
	 login = customerDetailsDAO.validateMobile(mobileNumber);
		}catch (Exception e) {
			throw new customerNotFoundException("Unable to validate customer as customer details are not available");
		
		}
	if(login.get(0).getPassword() != null)	{
		if (login.get(0).getPassword().equalsIgnoreCase(password)) {
			Product[] prod = getProducts();
			 
			 if(prod != null)
			 {
				 response= new ResponseEntity<Product[]>(prod, HttpStatus.OK);
			 }
			 
			 else
			 {				 
				 response= new ResponseEntity<Product[]>(prod,HttpStatus.NOT_FOUND);
			 }
		} 
		else {
			
			response = new ResponseEntity<Product[]>(HttpStatus.FORBIDDEN);
		}
	}
			return response;
	}
	
	/*
	 * Method: getProducts() 
	 * 
	 * Description : Service call for retrieving products which are in database.
	 * Here we have used restTemplate in order to provide interaction between two micro services.
	 */

	private Product[] getProducts() {
		final String url = "http://localhost:9090/getProductDetails";
		RestTemplate restTemplate = new RestTemplate();
		Product[] prod = restTemplate.getForObject(url, Product[].class);
		return prod;

	}

	@Override
	public ResponseEntity<String> addToCart(HttpServletRequest request) throws customerNotFoundException {
		
		if (null != request) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			float price = Float.parseFloat(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			final String url = "http://localhost:9090/addToCart";
			RestTemplate restTemplate = new RestTemplate();
			Cart newCart = new Cart();
			newCart.setCartPrice(price);
			newCart.setProductId(productId);
			newCart.setQuantity(quantity);
			MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
			mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
					Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
			restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
			restTemplate.postForObject(url, newCart, Cart.class, productId, quantity, price);
			return new ResponseEntity<String>("Product added to cart successfully", HttpStatus.ACCEPTED);
		}
		return null;
	}

}
