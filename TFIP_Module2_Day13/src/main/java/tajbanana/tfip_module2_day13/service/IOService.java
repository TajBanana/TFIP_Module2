package tajbanana.tfip_module2_day13.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tajbanana.tfip_module2_day13.contact.Contact;

import java.io.FileWriter;
import java.io.IOException;

public class IOService {
    private static final Logger logger =
            LoggerFactory.getLogger(IOService.class);

    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    public IOService(Contact contact) {
        this.id = contact.getID();
        this.name = contact.getName();
        this.email = contact.getEmail();
        this.phoneNumber = contact.getPhoneNumber();
    }

    public void write() {
        try {
            FileWriter contactFile = new FileWriter(
                    "existingcontacts/" + id +".txt");
            contactFile.write(id + "\n" +
                    name + "\n" +
                    email + "\n" +
                    phoneNumber);
            contactFile.close();

        } catch (IOException e) {
            logger.info("cannot write file, user might exists");
        }
    }

}
