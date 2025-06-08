package com.polarbookshop.catalogservice;


import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private  JacksonTester<Book> tester;

    @Test
    public void testCreateBook() throws Exception {
        var book = new Book("1234567890", "Title","Author", 9.90);
        var jsonContent = tester.write(book);
        assert jsonContent != null;
        assertThat(jsonContent).isNotNull();
        assertThat(jsonContent.getJson()).isNotNull();

    }

    @Test
    public void testDeserializeBook() throws Exception {
        var content = """
                    {
                    "isbn": "1234567890",
                    "title": "Title",
                    "auth": "Author",
                    "price": 9.90
                }""";
        assertThat(tester.parseObject(content))
                .isEqualTo(new Book("1234567890", "Title", "Author", 9.90));
    }
}
