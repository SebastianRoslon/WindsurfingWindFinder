package pl.roslon.WindsurfingWindFinder.webclient.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HourlyDto {

    private double[] windspeed_10m;
    private double[] temperature_2m;

}
