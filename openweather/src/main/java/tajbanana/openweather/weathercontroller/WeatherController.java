package tajbanana.openweather.weathercontroller;

import org.apache.catalina.LifecycleState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tajbanana.openweather.weathermodel.Weather;
import tajbanana.openweather.weatherservice.WeatherService;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping(path = "/weather", produces = MediaType.TEXT_HTML_VALUE)
public class WeatherController {
    private static final Logger logger =
            LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping()
    public String getWeather(Model model, @RequestParam String city) {
        logger.info("Input city: %S".formatted(city));

        List<Weather> weatherList = Collections.emptyList();

        try {
            weatherList = weatherService.getWeather(city);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

        if (weatherList.size() > 0)
            city = weatherList.get(0).getCityName();

        model.addAttribute("weather", weatherList);
        model.addAttribute("city", city);

        return weatherList;
    }

}
