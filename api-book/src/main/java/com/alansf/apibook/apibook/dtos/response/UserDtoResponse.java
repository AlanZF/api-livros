package com.alansf.apibook.apibook.dtos.response;

import com.alansf.apibook.apibook.models.Book;

import java.util.List;

public class UserDtoResponse {

    private Integer idUser;

    private String name;

    private String email;

    private List<Book> books;

    public UserDtoResponse() { }

    public UserDtoResponse(Integer idUser, String name, String email, List<Book> books) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.books = books;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
