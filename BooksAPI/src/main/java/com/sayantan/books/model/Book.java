package com.sayantan.books.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {
    private Long bookId;

    @NonNull
    private String bookName;

    @NonNull
    private String authorName;
    private int totalPage;
    private double price;
}
