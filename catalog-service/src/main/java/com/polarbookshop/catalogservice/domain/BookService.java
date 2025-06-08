package com.polarbookshop.catalogservice.domain;


import com.polarbookshop.catalogservice.domain.exceptions.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.domain.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;

    }

    public Iterable<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    public Book viewBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("The book with isbn" +isbn + " does not exist"));
    }

    public Book addBook(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())){
            throw new BookAlreadyExistsException("This book already exists with isbn" + book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book updateBook(Book book,String isbn) {
        return bookRepository.findByIsbn(isbn).map(
                existingBook->{
                    Book abook = new Book(
                            existingBook.isbn(),
                            book.title(),
                            book.auth(),
                            book.price()
                    );
                            return bookRepository.save(abook);
                }).orElseGet(()->addBook(book));
    }
}
