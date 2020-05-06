package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ExchangeValue;

@Repository
public interface ExchangeRepository extends MongoRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);
}
