package tajbanana.tfip_module2_day12.numbercontroller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import tajbanana.tfip_module2_day12.exception.NumberException;
import tajbanana.tfip_module2_day12.number.Number;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class NumberController {
    private static final Logger logger = LoggerFactory.getLogger(NumberController.class);

    @GetMapping("/")
    public String generateForm(Model model) {
        Number number = new Number();
        model.addAttribute("input", number);
        return "form";
    }

    @PostMapping("/generateImages")
    public String generateImages(Model model, @ModelAttribute  Number number) {
        logger.info("input number: " + number.getNumber());
        try {
            int userInput = number.getNumber();

            ArrayList<String> imageList = new ArrayList<>(Arrays.asList(
                    "number1.jpeg","number2.jpeg","number3.jpeg","number4.jpeg","number5.jpeg",
                    "number6.jpeg","number7.jpeg","number8.jpeg","number9.jpeg","number10.jpeg"
            ));

            logger.info(String.valueOf(imageList));

            int numberOfShuffles = 0;
            if (userInput >= imageList.size()) {
                numberOfShuffles = imageList.size();
            } else {
                numberOfShuffles = userInput;
            }

            for (int i = 0; i < numberOfShuffles; i++) {
                int randomIndex = (int) (Math.random()* (imageList.size()));
                int currentIndex = i;

                String randomIndexElement = imageList.get(randomIndex);

                imageList.set(randomIndex,imageList.get(currentIndex));
                imageList.set(currentIndex, randomIndexElement);
            }

            logger.info(String.valueOf(imageList));

            ArrayList<String> selectedImages = new ArrayList<>() ;
            for (int i = 0; i < userInput; i++) {
                selectedImages.add(imageList.get(i));
            }

            logger.info(String.valueOf(selectedImages));

            model.addAttribute("selectedImages",selectedImages);
            model.addAttribute("userNumber", userInput);
            return "result";

        } catch (NumberException e) {
            logger.error("wrong number lei");
            return "error";
        }

    }


}
