package pl.roslon.WindsurfingWindFinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;
import pl.roslon.WindsurfingWindFinder.webclient.WindClient;

@Controller
public class WindController {
    private WindClient windClient;
    private PointRepository pointRepository;

    public WindController(WindClient windClient, PointRepository pointRepository) {
        this.windClient = windClient;
        this.pointRepository = pointRepository;
    }

    @GetMapping("/index")
    private String printRepo(Model model) {
        model.addAttribute("pointsList", pointRepository.findAllByOrderByWindSpeedDesc());
        return "index.html";
    }

    @PostMapping("/saveNewPoint")
    private String addNewCity(@RequestParam String cityName){
        windClient.setCityName(cityName);
        windClient.createPointFromController();
        return "redirect:/index";
    }


    @PostMapping("/saveMultiplePoints")
    private String addMultipleCities(){
        windClient.setCityName("bobrowiec");
        windClient.createPointFromController();

        windClient.setCityName("piaseczno");
        windClient.createPointFromController();

        windClient.setCityName("warsaw");
        windClient.createPointFromController();

        windClient.setCityName("golkow");
        windClient.createPointFromController();

        windClient.setCityName("zabieniec");
        windClient.createPointFromController();
        return "redirect:/index";
    }

    @ResponseBody
    @PostMapping("/delete")
    private String deleteCity(@RequestParam String cityName){
        pointRepository.deleteByCityNameContainsIgnoreCase(cityName);
        return "ok";
    }

}
