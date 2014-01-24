package welp.config;

public class PathHelper {

    public String home() {
        return "/";
    }

    private final String RESTAURANTS_BASE_PATH = "/restaurants";

    public String newRestaurant() {
        return RESTAURANTS_BASE_PATH + "/new";
    }

    public String restaurants() {
        return RESTAURANTS_BASE_PATH;
    }
}
