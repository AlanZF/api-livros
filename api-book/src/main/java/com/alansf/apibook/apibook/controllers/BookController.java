package com.alansf.apibook.apibook.controllers;

import com.alansf.apibook.apibook.models.Book;
import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.services.BookService;
import com.alansf.apibook.apibook.services.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("apibook/book")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    Instant dateHourNow;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    public Book createBook(@RequestBody Book book, @PathVariable Integer idUser) {
        Optional<User> optUser = userService.findUserById(idUser);
        User user = optUser.get();

        dateHourNow = Instant.now();

        book.setUser(user);
        book.setCreated(dateHourNow);

        return bookService.createBook(book);
    }
}
