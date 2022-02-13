package tajbanana.marvelapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(path = "/character")
public class MarvelController {

    public static String randomChar() {
        String[] charList = {"wolverine", "charles xavier", "storm","daredevil", "iron+man" };
        Random random = new Random();
        int randomInt = random.nextInt(0, charList.length);
        System.out.printf(charList[randomInt]);
        return charList[randomInt];
    }

/*    @GetMapping()
    public ResponseEntity<String>*/

}
