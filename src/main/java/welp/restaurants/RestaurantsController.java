package welp.restaurants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/restaurants")
public class RestaurantsController {

    private final RestaurantService service;

    @Autowired
    public RestaurantsController(RestaurantService service) {
        this.service = service;
    }

    @RequestMapping(value = "/new", method = {RequestMethod.GET , RequestMethod.HEAD})
    public String newRestaurant(Restaurant Restaurant) {
        return "restaurants/form";
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("restaurants", service.getAllRestaurants());

        return "restaurants/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createRestaurant(@Valid Restaurant restaurant, Model model) {
        service.addRestaurant(restaurant);
        return "redirect:/restaurants/";
    }



}
