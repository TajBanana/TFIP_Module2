package tajbanana.tfip_module2_day13.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tajbanana.tfip_module2_day13.contact.Contact;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private static final Logger logger =
            LoggerFactory.getLogger(SearchService.class);
    private String contactID;
    private String currentLine;

    public SearchService(String contactID) {
        this.contactID = contactID;
    }

    public Contact contactSearch() {
        Path path = Paths.get("existingcontacts/" + contactID + ".txt");
        logger.info("Contact files dir: %s".formatted(path));
        if (Files.exists(path)) {
//            logger.info("Contact Exists");
            List<String> contactInfoList = new ArrayList<>();
            Contact contact = new Contact();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
                while ((currentLine = reader.readLine()) != null) {
                    contactInfoList.add(currentLine);
                    logger.info(String.valueOf(contactInfoList));
                }
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
            contact.setID(contactInfoList.get(0));
            contact.setName(contactInfoList.get(1));
            contact.setEmail(contactInfoList.get(2));
            contact.setPhoneNumber(contactInfoList.get(3));

            return contact;

        } else {
            logger.info("Contact file doesn't exist");
            return null;
        }

    }
}
