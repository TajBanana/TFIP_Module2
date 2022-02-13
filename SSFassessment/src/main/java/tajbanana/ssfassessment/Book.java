package tajbanana.ssfassessment;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Book {
    private String bookTitle;
    private String bookKey;
    private String bookDescription;
    private String bookExcerpt;
    private boolean bookCached;
    public static final Logger logger = LoggerFactory.getLogger(Book.class.getName());

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookKey() {
        return bookKey;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookExcerpt() {
        return bookExcerpt;
    }

    public void setBookExcerpt(String bookExcerpt) {
        this.bookExcerpt = bookExcerpt;
    }

    public boolean isBookCached() {
        return bookCached;
    }

    public void setBookCached(boolean bookCached) {
        this.bookCached = bookCached;
    }

    public static Book create(String jsonString) {
        try (InputStream is = new ByteArrayInputStream(jsonString.getBytes())) {
            final JsonReader reader = Json.createReader(is);
            return create(reader.readObject());
        } catch (Exception ex) {
            logger.warn("error creating book");
        }
        return new Book();
    }

    public static Book create(JsonObject o) {
        final Book b = new Book();
        b.setBookTitle(o.getString("title"));
        b.setBookKey(o.getString("key"));
        //TODO INSERT DESCRIPTION, EXCERPT, CACHE STATUS
        return b;
    }



}
