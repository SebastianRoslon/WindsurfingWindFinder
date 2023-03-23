package pl.roslon.WindsurfingWindFinder.service;

import org.springframework.stereotype.Service;
import pl.roslon.WindsurfingWindFinder.model.PointDto;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;
import java.util.ArrayList;
import java.util.List;


@Service
public class WindService {

    private final WeatherClient weatherClient;

    public WindService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }


    List<PointDto> pointDtoList = new ArrayList<>();
    public void addNewPointToList(){
        pointDtoList.add(weatherClient.buildPoint("zakopane"));
    }

    public String showPointsList(){
       return String.valueOf(pointDtoList.get(0));
    }

}
