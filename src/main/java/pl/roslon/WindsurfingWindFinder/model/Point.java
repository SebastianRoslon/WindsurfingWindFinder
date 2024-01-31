package pl.roslon.WindsurfingWindFinder.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Entity
@Setter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cityName;
    private double windSpeed;
    private double windTemp;

    public Point(String cityName, double windSpeed, double windTemp) {
        this.cityName = cityName;
        this.windSpeed = windSpeed;
        this.windTemp = windTemp;
    }
}
