package pl.roslon.WindsurfingWindFinder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.roslon.WindsurfingWindFinder.service.WindService;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

@Controller
@Slf4j
public class WindController {

    private final WeatherClient weatherClient;
    private final WindService windService;

    public WindController(WeatherClient weatherClient, WindService windService) {
        this.weatherClient = weatherClient;
        this.windService = windService;
    }

    @ResponseBody
    @GetMapping("/city/{city}")
    public String printCity(@PathVariable String city) {
        return weatherClient.geocode(city).toString();
    }

    @ResponseBody
    @GetMapping("/wind")
    public String printCity() {
        return weatherClient.windSpeed(51.25, 22.57).toString();
    }

    @ResponseBody
    @GetMapping("/main/{city}")
    public String printMain(@PathVariable String city) {
        return weatherClient.point(city).toString();
    }

    @ResponseBody
    @GetMapping("/avg/{city}")
    public String avgWind(@PathVariable String city) {
        return windService.calculateAverageWind(city);
    }

}
