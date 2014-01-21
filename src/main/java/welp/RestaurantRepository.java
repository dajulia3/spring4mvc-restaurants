package welp;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findById(long id);
}
