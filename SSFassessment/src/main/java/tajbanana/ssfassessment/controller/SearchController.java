package tajbanana.ssfassessment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tajbanana.ssfassessment.Book;
import tajbanana.ssfassessment.service.BookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(path = "/book")
public class SearchController {
    private final Logger logger = LoggerFactory.getLogger(SearchController.class.getName());

    @Autowired
    private BookService bookService;


    @GetMapping
    public String getBook(@RequestParam String searchTerm, Model model) {
        logger.info("Search for: " + searchTerm);
//        model.addAttribute(searchParam);

        List<Book> bookList = new ArrayList<>();
        try {
            bookList = bookService.search(searchTerm);
        } catch (Exception e) {
            logger.warn("WARNING!: %s".formatted(e.getMessage()));
        }

        model.addAttribute("bookList", bookList);

        return "book";
    }

}
