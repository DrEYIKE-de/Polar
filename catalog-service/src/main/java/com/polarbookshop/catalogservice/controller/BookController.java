package com.polarbookshop.catalogservice.controller;


import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookService.viewAllBooks();
    }

    @GetMapping("{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return bookService.viewBookByIsbn(isbn);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("{isbn}")
    public Book updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        return bookService.updateBook(book, isbn);
    }

    @DeleteMapping("{isbn}")
    public void deleteBook(@PathVariable String isbn) {
         bookService.removeBook(isbn);
    }


}
