package com.polarbookshop.catalogservice.domain.exceptions;

public class BookNotFoundException extends RuntimeException {
        public BookNotFoundException(String message) {
            super(message);
        }
}
