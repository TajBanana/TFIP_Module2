package tajbanana.ssfassessment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tajbanana.ssfassessment.Book;


@Service
public class BookCacheImpl {
    public static final Logger logger = LoggerFactory.getLogger(BookCacheImpl.class.getName());

    @Autowired
    BookCacheService bookCacheService;

    public Book getBook(String work_ID) {
        
        //TODO
        // to check if the work_ID exists in the redis repository
        // if it exists, use the returned json object to populate the site
        // if it doesn't exist, save it into the repository using the saveBook() method

        return null;
    }

}
