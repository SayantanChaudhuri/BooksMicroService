package com.sayantan.books.controllers;

import com.sayantan.books.exceptions.InvalidAuthorNameException;
import com.sayantan.books.model.Book;
import com.sayantan.books.model.Books;
import com.sayantan.books.services.BooksServiceImpl;
import com.sayantan.books.validators.BookCustomValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api")
public class BooksController {

    @Autowired
    BooksServiceImpl booksService;

    @Autowired
    BookCustomValidator bookCustomValidator;

    @GetMapping("/getAllBooks")
    public ResponseEntity<Books> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                                    @RequestParam(defaultValue = "bookName") String sortBy) {

         Books books = booksService.getAllBooks(pageNo, pageSize, sortBy);
         log.info("Books result : {}", books);
         return new ResponseEntity<Books>(books, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/saveBook")
    public ResponseEntity<Books> saveBook(@RequestBody @Valid Book book, BindingResult errors) {
        log.info("Book save entry : {}", book);

        bookCustomValidator.validate(book, errors);

        if(errors.hasErrors())
            throw new InvalidAuthorNameException(errors.getAllErrors().get(0).getDefaultMessage());

        Books books  = booksService.saveBook(book);
        log.info("Books save result : {}", books);
        return new ResponseEntity<Books> (books, new HttpHeaders(), HttpStatus.OK);
    }
}

