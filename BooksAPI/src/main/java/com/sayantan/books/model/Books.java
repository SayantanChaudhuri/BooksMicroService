package com.sayantan.books.model;

import com.sayantan.books.exceptions.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Books {
    private List<Book> books;
    private long totalBooks;
    private int totalPageCount;
    private ErrorResponse errors;
}
