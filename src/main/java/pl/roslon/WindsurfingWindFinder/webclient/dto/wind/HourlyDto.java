package pl.roslon.WindsurfingWindFinder.webclient.dto.wind;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class HourlyDto {

    private ArrayList<Double> windspeed_10m;

}
