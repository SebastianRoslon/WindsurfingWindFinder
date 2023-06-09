package pl.roslon.WindsurfingWindFinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
@Entity
public class PointDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String city;
   // @ElementCollection
  //  private double[] windSpeed;
  //  private double[] temperature;
//    private double lat;
//    private double lon;
    private double avgWindSpeed;
    private double avgTemp;

    public PointDto(String city, double avgWindSpeed, double avgTemp) {
        this.city = city;
        this.avgWindSpeed = avgWindSpeed;
        this.avgTemp = avgTemp;
    }
}
