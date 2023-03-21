package pl.roslon.WindsurfingWindFinder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.roslon.WindsurfingWindFinder.model.PointEntity;
import pl.roslon.WindsurfingWindFinder.repository.PointEntityRepository;
import pl.roslon.WindsurfingWindFinder.service.WindService;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

import java.net.URI;

@Controller
@Slf4j
public class WindController {

    private final WeatherClient weatherClient;
    private final WindService windService;
    private final PointEntityRepository pointEntityRepository;

    public WindController(WeatherClient weatherClient, WindService windService, PointEntityRepository pointEntityRepository) {
        this.weatherClient = weatherClient;
        this.windService = windService;
        this.pointEntityRepository = pointEntityRepository;
    }

    @ResponseBody
    @GetMapping("/main/{city}")
    public String printMain(@PathVariable String city) {
        return weatherClient.buildPoint(city).toString();
    }

    @ResponseBody
    @GetMapping("/avg/{city}")
    public String avgWind(@PathVariable String city) {
        return windService.calculateAverageWind(city);
    }


    @PostMapping("/save/{city}")
    ResponseEntity<PointEntity> savePoint(@PathVariable String city) {
        windService.addDefaultCitiesToDb();
        PointEntity savedPoint = windService.addNewPointToDb(city);
        URI savedPointUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPoint.getId())
                .toUri();
        return ResponseEntity.created(savedPointUri).body(savedPoint);
    }

}
