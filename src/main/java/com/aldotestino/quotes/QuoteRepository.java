package com.aldotestino.quotes;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuoteRepository extends MongoRepository<Quote, String> {

  List<Quote> findByAuthor(String author);
}
