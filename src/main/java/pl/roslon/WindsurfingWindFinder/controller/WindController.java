package pl.roslon.WindsurfingWindFinder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/printlist")
    public String printList() {
        windService.addNewPointToList();
        return windService.showPointsList();
    }
}
