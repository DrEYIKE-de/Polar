package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;


public class BookValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void whenValidBook_thenSuccess() {
        Book book = new Book("1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> constraintViolations = validator.validate(book);
        System.out.println(constraintViolations);
        assert constraintViolations.isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrect_InvalidBook_thenError() {
        Book book = new Book("ard1234567890","Learning spring","bestAuth",9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assert !violations.isEmpty();
        ConstraintViolation<Book> violation = violations.iterator().next();
        assert violation.getPropertyPath().toString().equals("isbn");
    }

    @Test
    void whenTitleDefinedButIncorrect_InvalidBook_thenError() {
        Book book = new Book("1234567890","Le","bestAuth",9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assert !violations.isEmpty();
        ConstraintViolation<Book> violation = violations.iterator().next();
        assert violation.getPropertyPath().toString().equals("title");
    }
}
