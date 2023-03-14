package pl.roslon.WindsurfingWindFinder.service;

import org.springframework.stereotype.Service;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

import java.text.DecimalFormat;

@Service
public class WindService {

    private final DecimalFormat df = new DecimalFormat("0.00");
    private final WeatherClient weatherClient;

    public WindService(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public String calculateAverageWind(String cityName) {
        double sum = 0;
        int count = 0;
        for (Double number : weatherClient.point(cityName).getWindSpeed()) {
            sum += number;
            count++;
        }
        if (count == 0) {
            return String.valueOf(0);
        }
        double average = sum / count;
        return "average wind speed in " + cityName + ": " + String.valueOf(df.format(average));
    }
}
