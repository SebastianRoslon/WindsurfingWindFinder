package pl.roslon.WindsurfingWindFinder.service;

import org.springframework.stereotype.Service;
import pl.roslon.WindsurfingWindFinder.model.PointEntity;
import pl.roslon.WindsurfingWindFinder.model.WindPoint;
import pl.roslon.WindsurfingWindFinder.repository.PointEntityRepository;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

import java.text.DecimalFormat;
import java.util.List;


@Service
public class WindService {

    private final DecimalFormat df = new DecimalFormat("0.00");
    private final WeatherClient weatherClient;
    private final PointEntityRepository pointEntityRepository;

    public WindService(WeatherClient weatherClient, PointEntityRepository pointEntityRepository) {
        this.weatherClient = weatherClient;
        this.pointEntityRepository = pointEntityRepository;
    }

    public String calculateAverageWind(String cityName) {
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
        pointEntityRepository.save(new PointEntity());
        pointEntityRepository.save(new PointEntity("Chalupy", weatherClient.windSpeed(54.75, 18.50).getWindSpeed(), 54.75, 18.50 ));
        pointEntityRepository.save(new PointEntity("Hel", weatherClient.windSpeed(54.60, 18.80).getWindSpeed(), 54.60, 18.80 ));
        List<Double> doubles = List.of(45.45, 44.44);
        pointEntityRepository.save(new PointEntity("Gdańsk", doubles, 21.45, 51.54));
    }

    public PointEntity addNewPointToDb(String cityName){
      WindPoint wp =  weatherClient.buildPoint(cityName);
      pointEntityRepository.save(wp);
        return pointEntityRepository.save(new PointEntity(cityName, weatherClient.windSpeed(weatherClient.geocode(cityName).getLat(),
                weatherClient.geocode(cityName).getLon()).getWindSpeed(),
                weatherClient.geocode(cityName).getLat(), weatherClient.geocode(cityName).getLon()));

    }

}
