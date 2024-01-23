package pl.roslon.WindsurfingWindFinder.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.roslon.WindsurfingWindFinder.dto.HourlyDto;
import pl.roslon.WindsurfingWindFinder.dto.RootDto;
import pl.roslon.WindsurfingWindFinder.model.Point;

@Component
public class WindClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String METEO_URL = "https://api.open-meteo.com/v1/";


    public Point createPointsList(){
        RootDto rootDto = callGetMethod(METEO_URL + "forecast?latitude=53.3833&longitude=14.6333&hourly=temperature_2m,wind_speed_10m&forecast_days=1", RootDto.class);
        return Point.builder()
                .windSpeed(rootDto.getHourly().getWind_speed_10m())
                .windTemp(rootDto.getHourly().getTemperature_2m())
                .build();
    }


    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }

}
