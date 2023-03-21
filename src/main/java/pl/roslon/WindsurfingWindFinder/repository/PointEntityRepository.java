package pl.roslon.WindsurfingWindFinder.repository;

import org.springframework.data.repository.CrudRepository;
import pl.roslon.WindsurfingWindFinder.model.PointEntity;

public interface PointEntityRepository extends CrudRepository<PointEntity, Long> {
}
