package com.alansf.apibook.apibook.models;

import com.alansf.apibook.apibook.enums.ReadStatus;
import java.util.Date;

public class Book {

    private Integer idBook;
    private String title;
    private String author;
    private Date created;
    private Date concluded;
    private Integer rating;
    private Integer status;

    private User user;

    Book() { }

    Book(Integer idBook, String title, String author, Date created,
         Date concluded, Integer rating, ReadStatus status) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.created = created;
        this.concluded = concluded;
        this.rating = rating;
        setStatus(status);
    }

    public Integer getIdBook() {
        return idBook;
    }
    public void setIdBook(Integer idBook) { this.idBook = idBook; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; };

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) { this.author = author; }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) { this.created = created; };

    public Date getConcluded() {
        return concluded;
    }
    public void setConcluded(Date concluded) { this.concluded = concluded; }

    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) { this.rating = rating; };

    public ReadStatus getStatus() {
        return ReadStatus.valueOf(this.status);
    }

    public void setStatus(ReadStatus status) {
        if(status != null) {
            this.status = status.getCode();
        }
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
