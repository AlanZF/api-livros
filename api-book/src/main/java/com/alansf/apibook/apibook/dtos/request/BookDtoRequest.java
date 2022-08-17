package com.alansf.apibook.apibook.dtos.request;

import com.alansf.apibook.apibook.enums.ReadStatus;
import com.alansf.apibook.apibook.models.User;

import javax.validation.constraints.NotNull;
import java.time.Instant;

public class BookDtoRequest {

    @NotNull
    private String title;
    @NotNull
    private String author;

    private Instant created;

    private Instant concluded;

    private Integer rating;
    @NotNull
    private Integer status;

    private User user;

    public BookDtoRequest(String title, String author, Instant created,
         Instant concluded, Integer rating, ReadStatus status) {
        this.title = title;
        this.author = author;
        this.created = created;
        this.concluded = concluded;
        this.rating = rating;
        setStatus(status);
    }

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
