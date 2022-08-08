package com.alansf.apibook.apibook.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="`User`")
@SequenceGenerator(name="seq_user", sequenceName="user_seq", allocationSize=1)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_user")
    @Column(name="idBook")
    private Integer idUser;

    @Column(name="name", nullable=false, length=45)
    private String name;
    @Column(name="email", nullable=true, length=45)
    private String email;
    @Column(name="password", nullable=true, length=16)
    private String password;
    @JsonManagedReference
    @OneToMany(mappedBy="user")
    private List<Book> books;

    public Integer getIdUser() { return idUser; }
    public void setIdUser(Integer idUser) { this.idUser = idUser; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
