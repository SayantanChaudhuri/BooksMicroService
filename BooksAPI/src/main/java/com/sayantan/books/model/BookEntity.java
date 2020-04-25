package com.sayantan.books.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookEntity {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @NonNull
    @Column(name = "book_name")
    private String bookName;

    @NonNull
    @Column(name = "author_name")
    private String authorName;

    @Column(name = "total_page")
    private int totalPage;

    @Column(name = "price")
    private double price;
}
