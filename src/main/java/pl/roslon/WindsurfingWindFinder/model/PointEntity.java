package pl.roslon.WindsurfingWindFinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String city;
   // @ElementCollection
    private List<Double> windSpeed;
    private double lat;
    private double lon;


    public PointEntity(String city, List<Double> windSpeed, double lat, double lon) {
        this.city = city;
        this.windSpeed = windSpeed;
        this.lat = lat;
        this.lon = lon;
    }
}
