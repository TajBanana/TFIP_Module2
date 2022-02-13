package tajbanana.ssfassessment.service;

import jakarta.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tajbanana.ssfassessment.Book;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static tajbanana.ssfassessment.Constants.OPEN_LIBRARY_URL_WORKS;

@Service
public class WorksService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class.getName());


    public Book search(String searchTerm) {
        final String url = OPEN_LIBRARY_URL_WORKS + searchTerm +".json";

        logger.info("URL: " + url);

        final RequestEntity<Void> req = RequestEntity.get(url).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                    "Error: status code %s".formatted(resp.getStatusCode().toString())
            );

        final String body = resp.getBody();
//        logger.info("payload: %s".formatted(body));

        Book book = new Book();

        try (InputStream is = new ByteArrayInputStream(body.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();

            String title = result.getString("title");
            logger.info("BOOK TITLE: " + title);
            book.setBookTitle(title);

            if (result.containsKey("description")) {
                final JsonObject descriptionObj = (JsonObject) result.get("description");
                String description = descriptionObj.getString("value");
                logger.info("BOOK DESCRIPTION: " + description);
                book.setBookDescription(description);
            } else {
                book.setBookDescription("There's no description");
            }

//            TODO some of the responses has an excerpts array, need to handle both json object and array
            if (result.containsKey("excerpts")) {
                final JsonArray excerptsArray = result.getJsonArray("excerpts");
                final JsonObject excerptObj = excerptsArray.getJsonObject(1);
                final String excerpt = excerptObj.getString("excerpt");
                logger.info("BOOK EXCERPTS: " + excerpt);
                book.setBookExcerpt(excerpt);
            } else {
                book.setBookExcerpt("There's no excerpt");
            }

            return book;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
