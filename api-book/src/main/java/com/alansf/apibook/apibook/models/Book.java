package com.alansf.apibook.apibook.models;

import com.alansf.apibook.apibook.enums.ReadStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="Book")
@SequenceGenerator(name="seq_book", sequenceName="book_seq", allocationSize=1)
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_book")
    @Column(name = "idBook")
    private Integer idBook;
    @Column(name="title", nullable=false, length=45)
    private String title;
    @Column(name="author", nullable=false, length=45)
    private String author;
    @Column(name="created", nullable=true)
    private Instant created;
    @Column(name="concluded", nullable=true)
    private Instant concluded;
    @Column(name="rating", nullable=true)
    private Integer rating;
    @Column(name="status", nullable=false)
    private Integer status;
    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="User_idUser", referencedColumnName="idUser")
    private User user;

    public Book() { }

    public Book(Integer idBook, String title, String author, Instant created,
         Instant concluded, Integer rating, ReadStatus status) {
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

    public Instant getCreated() {
        return created;
    }
    public void setCreated(Instant created) { this.created = created; };

    public Instant getConcluded() {
        return concluded;
    }
    public void setConcluded(Instant concluded) { this.concluded = concluded; }

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
