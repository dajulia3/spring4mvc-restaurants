//Not quite working yet....
// Need to figure out how NOT to import the web stuff...
// or just say to hell with it and import it all!
package welp.seeds;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.AbstractApplicationContext;
import welp.restaurants.Restaurant;
import welp.restaurants.RestaurantRepository;

import javax.persistence.Entity;

@EnableAutoConfiguration
@ComponentScan(
        basePackages = {"welp"},
        includeFilters = @ComponentScan.Filter(value = Entity.class, type = FilterType.ANNOTATION)
)
public class SeedDB {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SeedDB.class);
        RestaurantRepository repository = context.getBean(RestaurantRepository.class);

        repository.save(new Restaurant("Gino's East", "162 E Superior St", "Delicious gut-bomb in a pan."));
        repository.save(new Restaurant("Portillo's", "100 W Ontario St", "Dawgs, Italian Beef, Burgers... Very Chicago"));
    }
}
