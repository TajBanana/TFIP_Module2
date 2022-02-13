package tajbanana.openweather.weatherservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tajbanana.openweather.weathermodel.Weather;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static tajbanana.openweather.Contants.*;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(
            WeatherService.class.getName());
    private final String appID;

    public WeatherService() {
        appID = OPENWEATHER_KEY;
/*        if(null != key && key.trim().length() > 0) {
            appID = key;
        } else {
            appID = "abc123";
        }*/
        logger.info(appID); //how come it doesn't log? is it because Spring don't execute constructors?
    }

    public List<Weather> getWeather(String city) {
        final String URL = UriComponentsBuilder.fromUriString(URL_WEATHER)
                .queryParam("q", city)
                .queryParam("appid", appID)
                .queryParam("units", "metric")
                .toUriString();

        final RequestEntity<Void> req = RequestEntity.get(URL).build();
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response =
                template.exchange(req,String.class);

        if (response.getStatusCode() != HttpStatus.OK)
            throw new IllegalArgumentException(
                    "Error: status code %s".formatted(response.getStatusCode().toString())
            );

        final String body = response.getBody();
        logger.info("payload: %s".formatted(body));



        return body;
    }


}
