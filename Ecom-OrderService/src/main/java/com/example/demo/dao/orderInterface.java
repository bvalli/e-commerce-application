package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.FinalOrder;

public interface orderInterface extends MongoRepository<FinalOrder, Integer> {

	/*
	 * @Query("from FinalOrder where cust_id=:cust_id") public List<FinalOrder>
	 * findAllById(int cust_id);
	 */

	/*
	 * @Query("from FinalOrder.finalProductList where cust_id=:cust_id") public
	 * List<FinalOrder> findAllProductsById(int cust_id);
	 */
}
