package com.sayantan.books.services;

import com.sayantan.books.model.Book;
import com.sayantan.books.model.BookEntity;
import com.sayantan.books.model.Books;

import java.util.List;

public interface BooksService {
    Books getAllBooks(Integer pageNo, Integer pageSize, String sortBy);
    Books saveBook(Book book);

    BookEntity convertToEntity(Book book);

    Book convertToDto(BookEntity book);

    List<Book> convertToDtoList(List<BookEntity> bookEntities);
}
