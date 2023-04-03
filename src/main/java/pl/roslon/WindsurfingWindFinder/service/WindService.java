package pl.roslon.WindsurfingWindFinder.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import pl.roslon.WindsurfingWindFinder.model.PointDto;
import pl.roslon.WindsurfingWindFinder.repository.PointRepository;

import java.util.List;


@Service
public class WindService {

    private final PointRepository pointRepository;

    public WindService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public void addPointToDb(PointDto pointDto) {
        pointRepository.save(pointDto);
    }

    public List<PointDto> printSortedPointList() {
        return pointRepository.findAllByOrderByAvgWindSpeedDesc();
    }

    @Transactional
    public void deletePoint(@NotBlank String city) throws IllegalArgumentException {
        if (!pointRepository.existsByCity(city)) {
            throw new IllegalArgumentException("Point with city " + city + " does not exist.");
        }
        pointRepository.deleteByCity(city);
    }

}
