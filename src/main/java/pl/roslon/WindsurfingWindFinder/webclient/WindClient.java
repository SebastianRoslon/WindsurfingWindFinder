package pl.roslon.WindsurfingWindFinder.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.roslon.WindsurfingWindFinder.dto.RootDto;
import pl.roslon.WindsurfingWindFinder.model.Point;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        RootDto[] rootDto = callGetMethod(METEO_URL + "forecast?latitude=52.0814,54.6038,54.761,54.1756,53.3833&longitude=21.024,18.8035,17.5555,15.5834,14.6333&hourly=temperature_2m,wind_speed_10m&forecast_days=1", RootDto[].class);
        Point[] points = new Point[rootDto.length];
        for (int i = 0; i < rootDto.length; i++) {
            points[i] = new Point(numberFormatter(Arrays.stream(rootDto[0].getHourly().getWind_speed_10m())),
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
