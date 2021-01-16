package com.aldotestino.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

  @Autowired
  private final QuoteRepository quoteRepository;

  public QuoteService(QuoteRepository quoteRepository) {
    this.quoteRepository = quoteRepository;
  }

  public void addQuote(Quote quote) {
    quoteRepository.save(quote);
  }

  public List<Quote> getAllQuotes() {
    return quoteRepository.findAll();
  }

  public Quote getRandomQuote() {
    Random rand = new Random();
    List<Quote> quotes = getAllQuotes();
    return quotes.get(rand.nextInt(quotes.size()));
  }

  public List<Quote> getQuoteByAuthor(String author) {
    return quoteRepository.findByAuthor(author);
  }
}
