package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;


	@Test
	void contextLoads() {
    }

	@Test
	void shouldReturnBookCreated_whenPostBookRequest() {
		Book book = new Book("1234567890", "Title", "Author", 9.90);

		webTestClient.post()
				.uri("/books")
				.bodyValue(book)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(response -> {
					assertThat(response).isNotNull();
					assertThat(response.isbn()).isEqualTo("1234567890");

				});
	}

}
