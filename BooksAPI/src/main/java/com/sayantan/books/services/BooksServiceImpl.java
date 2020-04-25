package com.sayantan.books.services;

import com.sayantan.books.model.Book;
import com.sayantan.books.model.BookEntity;
import com.sayantan.books.model.Books;
import com.sayantan.books.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Books getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {
        log.info("{}, {}, {}", pageNo, pageSize, sortBy);
        Books books = new Books();
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, sortBy).ignoreCase();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(order));
        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);

        if (bookEntities.hasContent()) {
            books.setBooks(convertToDtoList(bookEntities.getContent()));
        } else {
            books.setBooks(new ArrayList<>());
        }

        books.setTotalPageCount(bookEntities.getTotalPages());
        books.setTotalBooks(bookEntities.getTotalElements());

        return books;
    }

    @Override
    public Books saveBook(Book book) {
        log.info("Book : {}", book);
        bookRepository.save(convertToEntity(book));
        return this.getAllBooks(0, 10, "bookName");
    }

    @Override
    public BookEntity convertToEntity(Book book) {
        return modelMapper.map(book, BookEntity.class);
    }

    @Override
    public Book convertToDto(BookEntity book) {
        return modelMapper.map(book, Book.class);
    }

    @Override
    public List<Book> convertToDtoList(List<BookEntity> bookEntities) {

        return bookEntities
                .stream()
                .map(book -> convertToDto(book))
                .collect(Collectors.toList());
    }
}
