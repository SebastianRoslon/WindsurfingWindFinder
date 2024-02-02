package pl.roslon.WindsurfingWindFinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    // @ResponseBody
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
}
