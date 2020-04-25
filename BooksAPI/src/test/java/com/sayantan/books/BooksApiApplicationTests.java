package com.sayantan.books;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.sayantan.books.model.Book;
import com.sayantan.books.model.Books;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = BooksApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BooksApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {

    }

    @Sql({ "schema.sql", "data.sql" })
    @Test
    public void testGetAllBooks()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/getAllBooks", Books.class)
                        .getBooks().size() == 3);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book(0L, "Gitanjali", "Rabindranath Tagore", 2000, 1200.00);
        ResponseEntity<Books> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/saveBook", book, Books.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
