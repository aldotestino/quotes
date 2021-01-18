package com.aldotestino.quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/quotes")
public class QuoteController {

  @Autowired
  private final QuoteService quoteService;

  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping
  public List<Quote> getAllQuotes() {
    return quoteService.getAllQuotes();
  }

  @PostMapping
  public void addQuote(@RequestBody Quote quote) {
    quoteService.addQuote(quote);
  }

  @GetMapping(path = "random")
  public Quote getRandomQuote() {
    return quoteService.getRandomQuote();
  }

  @DeleteMapping(path = "{id}")
  public void removeQuote(@PathVariable("id") String id) {
    quoteService.removeQuote(id);
  }

  @GetMapping(path = "{author}")
  public List<Quote> getQuoteByAuthor(@PathVariable("author") String author) {
    return quoteService.getQuoteByAuthor(author);
  }

}
