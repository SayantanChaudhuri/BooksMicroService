package com.sayantan.books.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayantan.books.controllers.BooksController;
import com.sayantan.books.model.Book;
import com.sayantan.books.model.Books;
import com.sayantan.books.services.BooksServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BooksServiceImpl booksService;

    private Books books;

    private Book book;

    @Before
    public void setUp() {

        book = new Book(0L, "Head First Design Patterns", "Eric Freeman, Bert Bates, Kathy Sierra", 676, 706.65);
        books = new Books(Arrays.asList(book), 1,1);

        Mockito.when(booksService.getAllBooks(0, 10, "bookName"))
                .thenReturn(books);

        Mockito.when(booksService.saveBook(book))
                .thenReturn(books);
    }

//    @Ignore
    @Test
    public void whenAllBooks_thenReturnJsonArray()
            throws Exception {

        given(booksService.getAllBooks(0, 10, "bookName")).willReturn(books);

        mvc.perform(MockMvcRequestBuilders.get("/api/getAllBooks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books", hasSize(1)))
                .andExpect(jsonPath("$.books[0].bookName").value(book.getBookName()));

        mvc.perform(MockMvcRequestBuilders.post("/api/saveBook")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books", hasSize(1)))
                .andExpect(jsonPath("$.books[0].bookName").value(book.getBookName()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
