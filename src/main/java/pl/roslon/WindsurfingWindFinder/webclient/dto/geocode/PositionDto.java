package pl.roslon.WindsurfingWindFinder.webclient.dto.geocode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {

    private double lat;
    private double lon;

}
