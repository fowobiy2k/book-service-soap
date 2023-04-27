package com.soap.service.services;

import com.fowobi.spring.soap.api.bookservice.*;
import com.soap.service.model.Book;
import com.soap.service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository repository;

    public CreateBookResponse save(CreateBookRequest request){
        CreateBookResponse response = new CreateBookResponse();

        Book newBook = new Book();
        newBook.setAuthor(request.getAuthor());
        newBook.setName(request.getName());
        newBook.setYear(request.getYear());

        Book book = repository.save(newBook);

        if(book != null){
            response.setResp("Saved successfully");
        } else {
            response.setResp("Failed");
            response.getErrorMessage().add("Attempt to save new book failed");
        }

        return response;
    }

    public GetBookByIdResponse findById(GetBookByIdRequest request){
        GetBookByIdResponse response = new GetBookByIdResponse();

        Book book = repository.findById(request.getId()).get();

        if(book != null){
            com.fowobi.spring.soap.api.bookservice.Book result = new com.fowobi.spring.soap.api.bookservice.Book();
            result.setAuthor(book.getAuthor());
            result.setName(book.getName());
            result.setYear(book.getYear());
            result.setId(book.getId());

            response.setBook(result);

        }

        return response;
    }

    public GetBooksResponse getAllBooks(GetBooksRequest request){
        GetBooksResponse response = new GetBooksResponse();

        List<Book> books = repository.findAll();
        logger.info("{}", books);

        if(books != null) {
            for (Book item : books){
                com.fowobi.spring.soap.api.bookservice.Book book = new com.fowobi.spring.soap.api.bookservice.Book();
                book.setId(item.getId());
                book.setYear(item.getYear());
                book.setName(item.getName());
                book.setAuthor(item.getAuthor());

                response.getBooks().add(book);
                logger.info("list of books: {}", response);
            }
        }
//        books.stream().map(item -> {
//            com.fowobi.spring.soap.api.bookservice.Book book = new com.fowobi.spring.soap.api.bookservice.Book();
//            book.setId(item.getId());
//            book.setYear(item.getYear());
//            book.setName(item.getName());
//            book.setAuthor(item.getAuthor());
//
//            response.getBooks().add(book);
//
////            return response;
//        });

        return response;
    }
}
