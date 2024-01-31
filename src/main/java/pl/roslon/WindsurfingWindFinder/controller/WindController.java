package pl.roslon.WindsurfingWindFinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.roslon.WindsurfingWindFinder.webclient.WindClient;

@Controller
public class WindController {
    private WindClient windClient;

    public WindController(WindClient windClient) {
        this.windClient = windClient;
    }

    @ResponseBody
    @GetMapping("/printList")
    private String printList() {
        return windClient.createDefaultPointsList();
    }

    @ResponseBody
    @GetMapping("/printRepo")
    private String printRepo() {
        return windClient.printRepository();
    }

    @ResponseBody
    @GetMapping("/createGeo")
    private String createGeocodePoint(@RequestParam String cityName) {
        windClient.setCityName(cityName);
        return String.valueOf(windClient.createPointFromController());
    }

}
