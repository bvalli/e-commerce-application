package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.userCart;


@Repository
public interface CustomerDetailsDAO extends CrudRepository<userCart, Integer> {

	@Query("from CustomerEntity where mobileNumber=:mobileNumber")
	public List<CustomerEntity> validateMobile(@Param(value="mobileNumber") String mobileNumber);

	
}
