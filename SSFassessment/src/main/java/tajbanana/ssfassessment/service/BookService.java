package tajbanana.ssfassessment.service;

import jakarta.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tajbanana.ssfassessment.Book;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tajbanana.ssfassessment.Constants.*;

@Service (BEAN_BOOK_SERVICE)
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class.getName());


    public List<Book> search(String searchTerm) {
        final String url = UriComponentsBuilder
                .fromUriString(OPEN_LIBRARY_URL)
                .queryParam("title", searchTerm.trim().replace(" ", "+"))
                .queryParam("fields","key,title")
                .queryParam("limit", 20)
                .toUriString();

        logger.info("URL: " + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                    "Error: status code %s".formatted(resp.getStatusCode().toString())
            );

        final String body = resp.getBody();
        logger.info("payload: %s".formatted(body));

        List<Book> listOfBooks = new ArrayList<>();

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray resultJsonArray = result.getJsonArray("docs");
            logger.info(String.valueOf(resultJsonArray));

            for (int i = 0; i < resultJsonArray.size(); i++) {
                Book book = new Book();
                JsonValue resultValue = resultJsonArray.get(i);
                logger.info(String.valueOf(resultValue));
                JsonObject resultObject = resultValue.asJsonObject();
                String workId = resultObject.getString("key");
                workId = workId.substring(7);
                logger.info(workId);
                String bookTitle = resultObject.getString("title");
                logger.info(bookTitle);
                book.setBookTitle(bookTitle);
                book.setBookKey(workId);
                listOfBooks.add(book);
            }

            return listOfBooks;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Collections.emptyList();
    }
}