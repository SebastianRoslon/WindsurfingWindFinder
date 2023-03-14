package pl.roslon.WindsurfingWindFinder.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.roslon.WindsurfingWindFinder.model.Geocode;
import pl.roslon.WindsurfingWindFinder.model.Point;
import pl.roslon.WindsurfingWindFinder.model.WindSpeed;
import pl.roslon.WindsurfingWindFinder.webclient.dto.geocode.RootGeocodeDto;
import pl.roslon.WindsurfingWindFinder.webclient.dto.wind.RootWindDto;

@Component
public class WeatherClient {

    private final String GEOCODE_URL = "https://api.tomtom.com/search/2/geocode/";
    private final String API_KEY = "I8DPoFkgjahuvD6XKl2K9NdXHiSJjGWQ";
    private final String METEO_URL = "https://api.open-meteo.com/v1/forecast?latitude=";
    private RestTemplate restTemplate = new RestTemplate();

    public Geocode geocode(String cityName) {
        RootGeocodeDto rootGeocodeDto = callGetMethod(GEOCODE_URL + "{city}.json?key={key}&limit=1", RootGeocodeDto.class, cityName, API_KEY);
        return Geocode.builder()
                .lat(rootGeocodeDto.getResults().get(0).getPosition().getLat())
                .lon(rootGeocodeDto.getResults().get(0).getPosition().getLon())
                .build();
    }

    public WindSpeed windSpeed(double lat, double lon) {
        RootWindDto rootWindDto = callGetMethod(METEO_URL + "{lat}&longitude={lon}&hourly=windspeed_10m&timezone=auto", RootWindDto.class, lat, lon);
        return WindSpeed.builder()
                .windSpeed(rootWindDto.getHourly().getWindspeed_10m())
                .build();
    }

    public Point point(String cityName) {
        return Point.builder()
                .windSpeed(windSpeed(geocode(cityName).getLat(), geocode(cityName).getLon()).getWindSpeed())
                .lat(geocode(cityName).getLat())
                .lon(geocode(cityName).getLon())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }
}
