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
public interface cartInterface extends CrudRepository<userCart, Integer> {

	@Query("select id from userCart where cust_id=:cust_id")
	public int findcartbyId(@Param(value="cust_id")int cust_id);
	
	
}
