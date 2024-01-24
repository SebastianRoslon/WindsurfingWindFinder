package pl.roslon.WindsurfingWindFinder.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
public class Point {

    private double windSpeed;
    private double windTemp;

}
