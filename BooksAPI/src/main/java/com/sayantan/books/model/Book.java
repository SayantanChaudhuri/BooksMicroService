package com.sayantan.books.model;

import com.sayantan.books.validators.AuthorNameSkipValidator;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class Book {
    private Long bookId;

    @NonNull
    @NotEmpty
    private String bookName;

    @NonNull
    @NotEmpty
    @AuthorNameSkipValidator
    private String authorName;

    @Min(value = 18)
    private int authorAge;

    private int totalPage;
    private double price;
}
