package pl.roslon.WindsurfingWindFinder.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.roslon.WindsurfingWindFinder.dto.RootDto;
import pl.roslon.WindsurfingWindFinder.model.Point;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.DoubleStream;

@Component
public class WindClient {

    private RestTemplate restTemplate = new RestTemplate();
    private PointRepository pointRepository;
    private final String METEO_URL = "https://api.open-meteo.com/v1/";

    public WindClient(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public String createPointsList() {
    String[] cities = {"Swinoujscie", "Pobierowo", "Kolobrzeg", "Ustka", "Leba", "Debki", "Chalupy", "Jastarnia", "Hel", "Sopot", "Krynica Morska", "Kadyny"};


        RootDto[] rootDto = callGetMethod(METEO_URL + "forecast?latitude=53.9105,54.061,54.1756,54.5805,54.761,54.8299,54.7592,54.6983,54.6038,54.4418,54.3805,54.2989&longitude=14.2471,14.9328,15.5834,16.8619,17.5555,18.0881,18.5089,18.6773,18.8035,18.56,19.4441,19.4856&hourly=temperature_2m,wind_speed_10m", RootDto[].class);
        Point[] points = new Point[rootDto.length];
        for (int i = 0; i < rootDto.length; i++) {
            points[i] = new Point(cities[i], numberFormatter(Arrays.stream(rootDto[i].getHourly().getWind_speed_10m())),
                    numberFormatter(Arrays.stream(rootDto[i].getHourly().getTemperature_2m())));
            pointRepository.save(points[i]);
        }
        Arrays.sort(points, Comparator.comparing(Point::getWindSpeed).reversed());
        return Arrays.toString(points);
    }

    private double numberFormatter(DoubleStream doubleStream) {
        double avgNumber = doubleStream.average().orElse(0.0);
        return (double) Math.round(avgNumber);
    }

    public String printRepository(){
       return pointRepository.findAll().toString();
    }
    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }

}
