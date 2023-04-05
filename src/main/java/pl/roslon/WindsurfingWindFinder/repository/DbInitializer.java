package pl.roslon.WindsurfingWindFinder.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.roslon.WindsurfingWindFinder.service.WindService;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

@Component
public class DbInitializer implements CommandLineRunner {

    private final WindService windService;
    private final WeatherClient weatherClient;

    public DbInitializer(WindService windService, WeatherClient weatherClient) {
        this.windService = windService;
        this.weatherClient = weatherClient;
    }

    @Override
    public void run(String... args) throws Exception {
        windService.addPointToDb(weatherClient.buildPoint("Hel"));
        Thread.sleep(500);
        windService.addPointToDb(weatherClient.buildPoint("Chalupy"));
        Thread.sleep(500);
        windService.addPointToDb(weatherClient.buildPoint("Kolobrzeg"));
    }
}
