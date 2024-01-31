package pl.roslon.WindsurfingWindFinder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Setter
@ToString
public class GeocodePoint {

    private String cityName;
    private double lat;
    private double lon;

}
