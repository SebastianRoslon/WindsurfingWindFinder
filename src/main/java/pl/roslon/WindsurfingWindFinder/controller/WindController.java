package pl.roslon.WindsurfingWindFinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    private String printList(){
       return windClient.createPointsList().toString();
    }

}
