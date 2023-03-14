package pl.roslon.WindsurfingWindFinder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Builder
@ToString
public class Point {

    private ArrayList<Double> windSpeed;
    private double lat;
    private double lon;
}
