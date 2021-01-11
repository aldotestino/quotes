package com.aldotestino.quotes;

import org.springframework.data.annotation.Id;

public class Quote {

  @Id
  private String id;
  private String author;
  private String content;

  public Quote() {}

  public Quote(String author, String content) {
    this.author = author;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getContent() {
    return content;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Quote{" +
            "id='" + id + '\'' +
            ", author='" + author + '\'' +
            ", content='" + content + '\'' +
            '}';
  }
}
