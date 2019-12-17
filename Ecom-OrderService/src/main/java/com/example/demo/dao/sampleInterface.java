package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MyCollection;

@Repository
public interface sampleInterface extends MongoRepository<MyCollection, Integer> {

}
