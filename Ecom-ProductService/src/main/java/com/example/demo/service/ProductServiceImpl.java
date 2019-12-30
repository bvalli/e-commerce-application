package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.Product;
import com.example.demo.entity.userCart;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductInterface;
import com.example.demo.repository.cartInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired

	private ProductInterface productInterface;

	@Autowired
	private ProductService productServiceImpl;

	
	@Override
	public List<Product> getCartdetails(int cust_id) throws ProductNotFoundException {
		List<Product> cart = productInterface.findCartDetailsByCustId(cust_id);
		if (cart != null && !cart.isEmpty()) {
			return cart.stream().distinct().collect(Collectors.toList());
		} else {
			throw new ProductNotFoundException("No Products in Cart");
		}

	}

	@Override
	public ResponseEntity<String> addToCart() {
		userCart userc = new userCart();
		List<Product> productList = new ArrayList<Product>();
		Product prod1 = Product.newProduct("1");
		productList.add(prod1);
		userc.setProductList(productList);
		CustomerEntity customerEntity1 = CustomerEntity.newCustomerEntity("1");
		userc.setCustomerEntity(customerEntity1);
		prod1.setCart(userc);
		Product prod2 = Product.newProduct("2");
		productList.add(prod2);
		userc.setCustomerEntity(customerEntity1);
		userc.setProductList(productList);
		//userc.getTotalOrderPrice();
		prod2.setCart(userc);
		userc.getTotalOrderPrice();
		Product cartout = productInterface.save(prod1);
		Product cartout1 = productInterface.save(prod2);
		if (cartout != null && cartout1 != null) {
			return new ResponseEntity<String>("Product added to cart successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Product could not be added to cart ", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public List<Product> getProductDetailsByProductId(int productId) throws ProductNotFoundException {
		List<Product> cartdet = productInterface.getProductDetailsByProdId(productId);
		if (cartdet != null && !cartdet.isEmpty()) {
			return cartdet.stream().distinct().collect(Collectors.toList());
		} else {
			throw new ProductNotFoundException("Requested product is not found ");
		}
	}

	@Override
	public List<Product> getProductDetails() throws ProductNotFoundException {
		List<Product> productData = (List<Product>) productInterface.findAll();
		if (productData != null && !productData.isEmpty()) {
			return productData.stream().distinct().collect(Collectors.toList());

		} else {
			throw new ProductNotFoundException("No Products to show up ");
		}

	}

	@Override
	public List<Product> removeFromCart(int id) {
		int cust_id = 209;
		List<Product> cartProducts = productInterface.findCartDetailsByCustId(cust_id);
		cartProducts.stream().forEach((Product) -> {
			if (Product.getId() == id) {
				productInterface.deleteById(id);
			}
		});

		return productServiceImpl.getCartdetails(cust_id);
	}

}
