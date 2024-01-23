package pl.roslon.WindsurfingWindFinder.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Point {

    private double[] windSpeed;
    private double[] windTemp;

}
