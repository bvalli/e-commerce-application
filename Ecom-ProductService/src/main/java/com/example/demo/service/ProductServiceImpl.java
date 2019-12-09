package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.userCart;
import com.example.demo.repository.ProductInterface;
import com.example.demo.repository.cartInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	
	private ProductInterface productInterface;
	
	@Autowired
	private cartInterface cartInterface;
	
	@Override
	public List<Product> getCartdetails(int cust_id) {
		List<Product> cart= productInterface.findCartDetailsByCustId(cust_id);
		
		return cart;
	}

	@Override
	public ResponseEntity<String> addToCart() {
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

	@Override
	public List<Product> getProductDetailsByProductId(int productId) {
		List<Product> cartdet=productInterface.getProductDetailsByProdId(productId);
		
		return cartdet;
	}

	@Override
	public List<Product> getProductDetails() {
		System.out.println("inside getProductDetails");
		List<Product> productData= (List<Product>) productInterface.findAll();
		
		
		return productData;

	}

}
