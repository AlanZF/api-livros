package com.alansf.apibook.apibook.controllers;

import com.alansf.apibook.apibook.dtos.request.BookDtoRequest;
import com.alansf.apibook.apibook.dtos.response.BookDtoResponse;
import com.alansf.apibook.apibook.enums.ReadStatus;
import com.alansf.apibook.apibook.models.Book;
import com.alansf.apibook.apibook.models.User;
import com.alansf.apibook.apibook.services.BookService;
import com.alansf.apibook.apibook.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookDtoResponse> createBook(@RequestBody BookDtoRequest bookRequest,
                                                      @PathVariable Integer idUser) {
        Optional<User> optUser = userService.findUserById(idUser);
        User user = optUser.get();
        bookRequest.setUser(user);

        dateHourNow = Instant.now();
        bookRequest.setCreated(dateHourNow);

        if (bookRequest.getStatus() != ReadStatus.READ) {
            bookRequest.setRating(0);
        } else {
            bookRequest.setRating(bookRequest.getRating());
        }

        if (bookRequest.getStatus() != ReadStatus.READ) {
            bookRequest.setConcluded(null);
        } else {
            bookRequest.setConcluded(bookRequest.getConcluded());
        }

        BookDtoResponse response = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listAllBooks")
    public ResponseEntity<List<BookDtoResponse>> listAllBooks() {
        List<BookDtoResponse> response = bookService.listAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/findBookById/{idBook}")
    public ResponseEntity<BookDtoResponse> findBookById(@PathVariable Integer idBook) {

        Optional<Book> optBook = bookService.findBookById(idBook);
        Book book = optBook.get();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookDtoResponse response = new BookDtoResponse();
        response = modelMapper.map(book, response.getClass());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updateBook/{idBook}")
    public ResponseEntity<BookDtoResponse> updateBook(@RequestBody BookDtoRequest bookRequest,
                           @PathVariable Integer idBook) {
        Optional<Book> optBook = bookService.findBookById(idBook);
        Book book = optBook.get();

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setStatus(bookRequest.getStatus());

        if (bookRequest.getStatus() != ReadStatus.READ) {
            book.setConcluded(null);
        } else {
            book.setConcluded(bookRequest.getConcluded());
        }

        if (bookRequest.getStatus() != ReadStatus.READ) {
            book.setRating(0);
        } else {
            book.setRating(bookRequest.getRating());
        }

        BookDtoResponse response = bookService.updateBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/deleteBook/{idBook}")
    public ResponseEntity<?> deleteBookById(@PathVariable Integer idBook) {
        Optional<Book> optBook = bookService.findBookById(idBook);
        Book book = optBook.get();

        bookService.deleteBook(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
