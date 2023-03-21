package pl.roslon.WindsurfingWindFinder.service;

import org.springframework.stereotype.Service;
import pl.roslon.WindsurfingWindFinder.model.Geocode;
import pl.roslon.WindsurfingWindFinder.model.PointEntity;
import pl.roslon.WindsurfingWindFinder.repository.PointEntityRepository;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

import java.text.DecimalFormat;


@Service
public class WindService {

    private final DecimalFormat df = new DecimalFormat("0.00");
    private final WeatherClient weatherClient;
    private final PointEntityRepository pointEntityRepository;

    public WindService(WeatherClient weatherClient, PointEntityRepository pointEntityRepository) {
        this.weatherClient = weatherClient;
        this.pointEntityRepository = pointEntityRepository;
    }

    public String calculateAverageWindSpeed(String cityName) {
        double sum = 0;
        int count = 0;
        for (Double number : weatherClient.buildPoint(cityName).getWindSpeed()) {
            sum += number;
            count++;
        }
        if (count == 0) {
            return String.valueOf(0);
        }
        double average = sum / count;
        return "average wind speed in " + cityName + ": " + df.format(average);
    }


    public void addDefaultCitiesToDb(){
        pointEntityRepository.save(new PointEntity("Chalupy", weatherClient.windSpeed(54.75, 18.50).getWindSpeed(), 54.75, 18.50 , calculateAverageWindSpeed("Chalupy")));
        pointEntityRepository.save(new PointEntity("Hel", weatherClient.windSpeed(54.60, 18.80).getWindSpeed(), 54.60, 18.80, calculateAverageWindSpeed("Hel") ));
    }

    public PointEntity addNewPointToDb(String cityName){
        Geocode geocode = weatherClient.geocode(cityName);
        return pointEntityRepository.save(new PointEntity(cityName,
                weatherClient.windSpeed(geocode.getLat(), geocode.getLon()).getWindSpeed(),
                geocode.getLat(),
                geocode.getLon(),
                calculateAverageWindSpeed(cityName)));
    }

}
