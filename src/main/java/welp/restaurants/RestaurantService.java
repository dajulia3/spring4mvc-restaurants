package welp.restaurants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;


    public Restaurant addRestaurant(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Iterable<Restaurant> getAllRestaurants() {
        return repository.findAll();
    }
}
