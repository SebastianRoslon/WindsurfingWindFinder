package pl.roslon.WindsurfingWindFinder.repository;

import org.springframework.data.repository.CrudRepository;
import pl.roslon.WindsurfingWindFinder.model.Point;

public interface PointRepository extends CrudRepository<Point, Long> {
}
