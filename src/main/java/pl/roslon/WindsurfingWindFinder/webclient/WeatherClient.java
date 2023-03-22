package pl.roslon.WindsurfingWindFinder.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.roslon.WindsurfingWindFinder.model.PointDto;
import pl.roslon.WindsurfingWindFinder.webclient.dto.geocode.PositionDto;
import pl.roslon.WindsurfingWindFinder.webclient.dto.geocode.RootGeocodeDto;
import pl.roslon.WindsurfingWindFinder.webclient.dto.weather.HourlyDto;
import pl.roslon.WindsurfingWindFinder.webclient.dto.weather.RootTemperatureDto;

import java.util.Arrays;

@Component
public class WeatherClient {

    private final String GEOCODE_URL = "https://api.tomtom.com/search/2/geocode/";
    private final String API_KEY = "I8DPoFkgjahuvD6XKl2K9NdXHiSJjGWQ";
    private final String METEO_URL = "https://api.open-meteo.com/v1/forecast?latitude=";
    private RestTemplate restTemplate = new RestTemplate();


    public PositionDto geocode(String cityName) {
        RootGeocodeDto rootGeocodeDto = callGetMethod(GEOCODE_URL + "{city}.json?key={key}&limit=1", RootGeocodeDto.class, cityName, API_KEY);
        return PositionDto.builder()
                .lat(rootGeocodeDto.getResults().get(0).getPosition().getLat())
                .lon(rootGeocodeDto.getResults().get(0).getPosition().getLon())
                .build();
    }

    public HourlyDto weather(double lat, double lon) {
        RootTemperatureDto rootTemperatureDto = callGetMethod(METEO_URL + "{lat}&longitude={lon}&hourly=temperature_2m,windspeed_10m&forecast_days=1&timezone=auto", RootTemperatureDto.class, lat, lon);
        return HourlyDto.builder()
                .windspeed_10m(rootTemperatureDto.getHourly().getWindspeed_10m())
                .temperature_2m(rootTemperatureDto.getHourly().getTemperature_2m())
                .build();
    }

    public PointDto buildPoint(String cityName) {
        return PointDto.builder()
                .city(cityName)
                .avgTemp(Arrays.stream(weather(geocode(cityName).getLat(), geocode(cityName).getLon()).getTemperature_2m())
                        .average()
                        .getAsDouble())
                .avgWindSpeed(Arrays.stream(weather(geocode(cityName).getLat(), geocode(cityName).getLon()).getWindspeed_10m())
                        .average()
                        .getAsDouble())
 //               .windSpeed(weather(geocode(cityName).getLat(), geocode(cityName).getLon()).getWindspeed_10m())
 //               .temperature(weather(geocode(cityName).getLat(), geocode(cityName).getLon()).getTemperature_2m())
//                .lat(geocode(cityName).getLat())
//                .lon(geocode(cityName).getLon())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }


}
