package pl.roslon.WindsurfingWindFinder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Geocode {

    private double lat;
    private double lon;

}
