package pl.roslon.WindsurfingWindFinder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.roslon.WindsurfingWindFinder.model.PointDto;

import java.util.List;

@Repository
public interface PointRepository extends CrudRepository<PointDto, Long> {

    List<PointDto> findAllByOrderByAvgWindSpeedDesc();

    long deleteByCity(String city);

}
