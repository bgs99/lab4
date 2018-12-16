package bgs.repo;
import bgs.model.Point;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PointRepository extends CrudRepository<Point, Integer> {
    Point findById(int id);
    List<Point> findAllByUsersession(String session);
    void deleteAllByIdAndUsersession(int id, String session);
}
