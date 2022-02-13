package tajbanana.ssfassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tajbanana.ssfassessment.Book;
import tajbanana.ssfassessment.service.WorksService;

@Controller
public class BookController {

    @Autowired
    WorksService worksService;

    @GetMapping("/book/{worksId}")
    public String getBookDetails(@PathVariable String worksId, Model model) {
        Book book = worksService.search(worksId);
        model.addAttribute("book",book);

        return "bookdetails";
    }


}
