package weather.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weather.services.WeatherServiceImpl;

@RestController
@RequestMapping("/weather")
public class DefaultController {

    private final WeatherServiceImpl weatherService;

    public DefaultController(WeatherServiceImpl weatherService) {
        this.weatherService = weatherService;
    }

    @PostMapping()
    public String post(@RequestParam String city) {
        return weatherService.getTemp(city);
    }
}
