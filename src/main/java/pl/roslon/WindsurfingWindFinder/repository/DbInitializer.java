package pl.roslon.WindsurfingWindFinder.repository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.roslon.WindsurfingWindFinder.webclient.WindClient;

@Component
public class DbInitializer implements CommandLineRunner {

    private final WindClient windClient;

    public DbInitializer(WindClient windClient) {
        this.windClient = windClient;
    }

    @Override
    public void run(String... args) throws Exception {
        windClient.createDefaultPointsList();
    }
}
