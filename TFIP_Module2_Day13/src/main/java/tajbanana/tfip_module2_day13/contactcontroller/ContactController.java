package tajbanana.tfip_module2_day13.contactcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tajbanana.tfip_module2_day13.contact.Contact;
import tajbanana.tfip_module2_day13.service.IOService;
import tajbanana.tfip_module2_day13.service.SearchService;

@Controller
public class ContactController {
    public static final Logger logger =
            LoggerFactory.getLogger(ContactController.class);

    @GetMapping("/")
    public String showContactControllerForm(Model model){
            Contact contact = new Contact();
            model.addAttribute("Contact", contact);
            model.addAttribute("Contact", contact);
            return "createContactForm";
    }

    @PostMapping("/contact")
    public String submitContactForm(Model model,
                                    @ModelAttribute Contact contact) {
        model.addAttribute("name", contact.getName());
        model.addAttribute("email", contact.getEmail());
        model.addAttribute("phoneNumber",
                contact.getPhoneNumber());
        model.addAttribute("ID", contact.getID());

        IOService contactIO = new IOService(contact);
        contactIO.write();

        logger.info("Name: " + contact.getName());
        logger.info("email: " + contact.getEmail());
        logger.info("phone: " + contact.getPhoneNumber());
        logger.info("ID: " + contact.getID());
        return "successful";
    }

    @GetMapping("/getContactByID")
    public String getContactByID(Model model, @RequestParam String ID) {

        logger.info("getting contact by ID");
        SearchService searchService = new SearchService(ID);
        Contact contact = searchService.contactSearch();

        if (contact != null) {
            model.addAttribute("name", contact.getName());
            model.addAttribute("email", contact.getEmail());
            model.addAttribute("phoneNumber",
                    contact.getPhoneNumber());
            model.addAttribute("ID", contact.getID());
            return "successful";
        } else {
            return "index";
        }
    }

    @GetMapping("/home")
    public String goHome() {
        return "createContactForm";
    }
}