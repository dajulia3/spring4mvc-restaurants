package features;

import features.support.WelpAppFluentTestNG;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@Test
public class RestaurantsWorkflowTests extends WelpAppFluentTestNG {

    public void title_of_bing_should_contain_search_query_name() {
        System.out.println("yay");

        goToPath("/");
        assertThat(title()).contains("Welp | Home Page");
    }

}
