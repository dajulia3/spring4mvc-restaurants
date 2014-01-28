package features;

import features.support.IntegrationBase;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@Test
//@ContextConfiguration(classes = TestConfiguration.class)
public class RestaurantsWorkflowTests extends IntegrationBase {

    public void title_of_bing_should_contain_search_query_name() {
        System.out.println("yay");

        goTo("http://localhost:9000/");
//        goToPath("/");
        assertThat(title()).contains("Welp | Home Page");
    }

}
