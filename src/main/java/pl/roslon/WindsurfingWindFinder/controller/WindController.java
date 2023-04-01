package pl.roslon.WindsurfingWindFinder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.roslon.WindsurfingWindFinder.model.PointDto;
import pl.roslon.WindsurfingWindFinder.service.WindService;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

import java.util.List;

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
    @GetMapping("/addPoint")
    public String addNewPointToList(@RequestParam String cityName) {
        windService.addPointToDb(weatherClient.buildPoint(cityName));
       return "ok";
    }

    @ResponseBody
    @GetMapping("/printList")
    public List<PointDto> printList() {
       return windService.printSortedPointList();

    }

}
