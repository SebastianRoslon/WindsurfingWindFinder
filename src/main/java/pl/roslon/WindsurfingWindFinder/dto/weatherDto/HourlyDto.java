package pl.roslon.WindsurfingWindFinder.dto.weatherDto;

import lombok.Getter;
import lombok.ToString;

@Getter
public class HourlyDto {

    private double[] wind_speed_10m;
    private double[] temperature_2m;

}
