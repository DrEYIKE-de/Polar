package com.polarbookshop.catalogservice.domain;

import jakarta.validation.constraints.*;

public record Book(
    @NotBlank(message = "Isbn of book is required")
    @Size(max = 13)
    @Pattern(
            regexp = "^([0-9]{10}|[0-9]{13})$",
            message = "The ISBN must have correct numbers of elements"
    )
    String isbn,

    @NotBlank(message = "Book title must be defined")
    @Size(min =3)
    String title,

    @NotBlank(message = "The author of the book must be defined")
    String auth,

    @NotNull(message = "The price of the book must be defined")
    @Positive(message = "The price must be positive")
    Double price

) {}
