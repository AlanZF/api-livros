package com.alansf.apibook.apibook.services;

import com.alansf.apibook.apibook.models.Book;
import com.alansf.apibook.apibook.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Integer idBook) {
        return bookRepository.findById(idBook);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Integer idBook) {
        bookRepository.deleteById(idBook);
    }
}
