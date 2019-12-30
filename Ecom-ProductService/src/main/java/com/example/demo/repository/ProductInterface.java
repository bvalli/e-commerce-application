package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.userCart;

@Repository
public interface ProductInterface extends CrudRepository<Product, Integer> {

	@Query("from Product where product_id=:product_id")
	public List<Product> getProductDetailsByProdId(@Param(value="product_id") int product_id);
	
	@Query("from Product where cartId in (select id from userCart where cust_id=:cust_id )")
	public List<Product> findCartDetailsByCustId(@Param(value="cust_id")int cust_id);


	
}
