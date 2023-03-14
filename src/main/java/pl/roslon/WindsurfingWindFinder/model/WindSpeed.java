package pl.roslon.WindsurfingWindFinder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Builder
@ToString
@Getter
public class WindSpeed {

    private ArrayList<Double> windSpeed;

}
