package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FinalOrder;
import com.example.demo.entity.Orders;

@Repository
public interface orderInterface extends JpaRepository<FinalOrder, Integer> {

	@Query("from FinalOrder where cust_id=:cust_id")
	public List<FinalOrder> findAllById(int cust_id);
	
	/*
	 * @Query("from FinalOrder.finalProductList where cust_id=:cust_id") public
	 * List<FinalOrder> findAllProductsById(int cust_id);
	 */
}
