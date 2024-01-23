package pl.roslon.WindsurfingWindFinder.dto;

import lombok.Getter;

@Getter
public class HourlyDto {

    private double[] wind_speed_10m;
    private double[] temperature_2m;

}
