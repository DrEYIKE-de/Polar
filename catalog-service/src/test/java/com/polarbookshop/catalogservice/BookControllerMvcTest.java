package com.polarbookshop.catalogservice;


import com.polarbookshop.catalogservice.controller.BookController;
import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import com.polarbookshop.catalogservice.domain.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
public class BookControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void shouldReturn404_whenGetBookByIsbn_DoesntExists() throws Exception {
        String bookIsbn = "123";
        given(bookService.viewBookByIsbn(bookIsbn))
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(get("/books/"+bookIsbn))
                .andExpect(status().isNotFound());
    }


    @Test
    void shouldReturnJsonBook_whenGetBookByIsbnExists() throws Exception {
        Book book = new Book("1234567890", "Title", "Author", 9.90);
       given(bookService.addBook(book)).willReturn(book);
        mockMvc.perform(MockMvcRequestBuilders.get("/books/"+book.isbn())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
