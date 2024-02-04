package pl.roslon.WindsurfingWindFinder.webclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.roslon.WindsurfingWindFinder.dto.geocodeDto.RootGeocodeDto;
import pl.roslon.WindsurfingWindFinder.dto.weatherDto.RootWeatherDto;
import pl.roslon.WindsurfingWindFinder.model.Point;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;

import java.util.Arrays;

@Component
public class WindClient {

    private RestTemplate restTemplate = new RestTemplate();
    private PointRepository pointRepository;
    private final String METEO_URL = "https://api.open-meteo.com/v1/";
    private final String GEOCODE_URL = "https://geocoding-api.open-meteo.com/v1/";

    private String cityName;
    private double lat;
    private double lon;

    public String setCityName(String cityName) {
        this.cityName = cityName;
        return cityName;
    }

    public WindClient(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public void createPointFromCityName() {
        getLatLonFromCityName();
        RootWeatherDto rootWeatherDto = callGetMethod(METEO_URL + "forecast?latitude={lat}&longitude={lon}&hourly=temperature_2m,wind_speed_10m&forecast_days=1", RootWeatherDto.class, lat, lon);

        Point p = new Point(cityName,
                valueFormatter(rootWeatherDto.getHourly().getWind_speed_10m()),
                valueFormatter(rootWeatherDto.getHourly().getTemperature_2m()));

        if (pointRepository.findByCityNameIgnoreCase(p.getCityName()) == null) {
            pointRepository.save(p);
        }
    }

    private void getLatLonFromCityName() {
        RootGeocodeDto rootGeocodeDto = callGetMethod(GEOCODE_URL + "search?name={cityName}&count=1&language=en&format=json", RootGeocodeDto.class, cityName);
       try {
           lat = rootGeocodeDto.getResults()[0].getLatitude();
           lon = rootGeocodeDto.getResults()[0].getLongitude();
       }catch (NullPointerException e){
           e.printStackTrace();
       }
    }

    public void createDefaultPointsList() throws InterruptedException {
        String[] defaultCities = {"Pobierowo", "Ustka", "Łeba", "Dębki", "Chałupy", "Jastarnia", "Hel", "Sopot", "Krynica Morska", "Kadyny"};

        for (int i = 0; i < defaultCities.length; i++) {
            defaultCities[i] = setCityName(defaultCities[i]);
            createPointFromCityName();
        }
    }

    private double valueFormatter(double[] doubles) {
        double d = Arrays.stream(doubles).average().orElse(0.0);
        return Math.floor(d * 100) / 100;
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(url, responseType, objects);
    }

}
