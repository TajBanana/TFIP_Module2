package tajbanana.fortunecookie.controller;

import jakarta.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tajbanana.fortunecookie.model.FortuneCookie;

import java.util.List;

@RestController
@RequestMapping(path = "/cookies",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CookiesRestController {

    @Autowired
    private FortuneCookie fortuneCookie;

    @GetMapping
    public ResponseEntity<String> userCookie(
            @RequestParam(defaultValue = "1") Integer count) {
        if (count < 1 || count > 10) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(
                        Json.createObjectBuilder()
                            .add("error","count must be between 1 and 10 inclusive")
                            .build()
                            .toString()
                    );


            /*            JsonObject errorObj = Json.createObjectBuilder().add
                    ("error","count must be between 1 and 10 inclusive")
                    .build();

            ResponseEntity<String> re = new ResponseEntity<>(
                    "count must be between 1 & 10 inclusive",
                    HttpStatus.BAD_REQUEST);

            ResponseEntity.badRequest()
                    .body("count must be between 1 & 10 inclusive");

            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("count must be between 1 & 10 inclusive");*/

        }

        List<String> cookies = this.fortuneCookie.getCookies(count);
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        cookies
                .parallelStream()
                //.stream()
                //.forEach(v -> arrBuilder.add(v));
                .reduce(
                        arrBuilder,  //identity
                        (ab, item) -> ab.add(item), // accumulator
                        (ab0, ab1) -> {
                            JsonArray a = ab1.build();
                            for (int i = 0; i < a.size(); i++)
                                ab0.add(a.get(i));
                            return ab0;
                        } // combiner
                );

        JsonObjectBuilder jsonObj = Json.createObjectBuilder()
                .add("cookies", arrBuilder)
                .add("timestamp", System.currentTimeMillis());

        return ResponseEntity.ok(jsonObj.build().toString());
        //TODO Error - 404 return json with {"error:"msg"} key value pair

        //TODO OK - 200 return json with cookies list {cookies:[...,...,...],
        // timestamp:<timestamp in milliseconds>}
    }
}
