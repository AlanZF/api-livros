package com.alansf.apibook.apibook.services;

import com.alansf.apibook.apibook.dtos.request.BookDtoRequest;
import com.alansf.apibook.apibook.dtos.response.BookDtoResponse;
import com.alansf.apibook.apibook.models.Book;
import com.alansf.apibook.apibook.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDtoResponse createBook(BookDtoRequest bookRequest) {
        Book book = new Book();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        book = modelMapper.map(bookRequest, book.getClass());
        bookRepository.save(book);

        BookDtoResponse response = new BookDtoResponse();
        response = modelMapper.map(book, response.getClass());

        return response;
    }

    public List<BookDtoResponse> listAllBooks() {
        List<Book> books = bookRepository.findAll();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return books.stream().map(book -> modelMapper
                .map(book, BookDtoResponse.class)).collect(Collectors.toList());
    }

    public Optional<Book> findBookById(Integer idBook) {
        return bookRepository.findById(idBook);
    }

    public BookDtoResponse updateBook(Book book) {
        bookRepository.save(book);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        BookDtoResponse response = new BookDtoResponse();
        response = modelMapper.map(book, response.getClass());

        return response;
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
