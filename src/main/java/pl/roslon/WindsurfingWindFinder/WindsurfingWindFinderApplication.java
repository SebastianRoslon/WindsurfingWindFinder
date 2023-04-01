package pl.roslon.WindsurfingWindFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;
import pl.roslon.WindsurfingWindFinder.service.WindService;
import pl.roslon.WindsurfingWindFinder.webclient.WeatherClient;

@SpringBootApplication
@EnableAsync
public class WindsurfingWindFinderApplication {

	public static void main(String[] args) {

		SpringApplication.run(WindsurfingWindFinderApplication.class, args);

	}

}
