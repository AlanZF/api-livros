package com.alansf.apibook.apibook.controllers;

import com.alansf.apibook.apibook.models.Book;
import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.services.BookService;
import com.alansf.apibook.apibook.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
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

    @PostMapping("/createBook/{idUser}")
    public Book createBook(@RequestBody Book book, @PathVariable Integer idUser) {
        Optional<User> optUser = userService.findUserById(idUser);
        User user = optUser.get();

        dateHourNow = Instant.now();

        book.setUser(user);
        book.setCreated(dateHourNow);

        return bookService.createBook(book);
    }

    @GetMapping("/listAllBooks")
    public List<Book> listAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/findBookById/{idBook}")
    public Optional<Book> findBookById(Integer idBook) {
        return bookService.findBookById(idBook);
    }

    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/deleteBook/{idBook}")
    public void deleteBookById(@PathVariable Integer idBook) {
        bookService.deleteBookById(idBook);
    }
}
