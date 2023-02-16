package com.vincent.swagger2.demo;

import com.vincent.swagger2.demo.entity.Book;
import com.vincent.swagger2.demo.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2DemoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetBook() {
        Book book = Book.builder()
                .name("被討厭的勇氣：自我啟發之父「阿德勒」的教導")
                .author("岸見一郎")
                .build();
        book = bookRepository.save(book);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/api/v1/book/{bookId}");
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("bookId", book.getBookid());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                builder.buildAndExpand(uriParams).toUri().toString(),
                HttpMethod.GET, entity, String.class);
        assertTrue("testGetBook Fail:\n" + response.getBody(),
                response.getStatusCode().is2xxSuccessful());
    }
}
