package pl.roslon.WindsurfingWindFinder.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.roslon.WindsurfingWindFinder.model.Point;

import java.util.List;

public interface PointRepository extends CrudRepository<Point, Long> {
    @Transactional
    @Modifying
    @Query("delete from Point p where upper(p.cityName) like upper(concat('%', ?1, '%'))")
    int deleteByCityNameContainsIgnoreCase(String cityName);
    long deleteByCityName(String cityName);

    Point findByCityNameIgnoreCase(String cityName);

    List<Point> findAllByOrderByWindSpeedDesc();

}
