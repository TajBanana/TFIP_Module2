package tajbanana.ssfassessment.service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tajbanana.ssfassessment.Book;
import tajbanana.ssfassessment.repository.BookRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCacheService {
    private static final Logger logger = LoggerFactory.getLogger(BookCacheService.class.getName());

    @Autowired
    BookRepository bookRepository;

    public void saveBook(String works_Id, Book book) {
        //TODO
        // save title, description, excerpt as string to redis
        // to pull the properties out from the book and create a json string and save it to redis
        String jsonString = "";
        bookRepository.save(works_Id, jsonString);
    }

    public Optional<Book> getBook(String works_Id) {
        Optional<String> opt = bookRepository.get(works_Id);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        //TODO
        // if opt exist, get the json string from the repo and parse it into
        // a json object. read from json object and pass the title, description, excerpts into model.

        return null;
    }
}