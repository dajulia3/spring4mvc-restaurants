package features;

import features.support.WelpFluentTestNG;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@Test
public class RestaurantsWorkflowTests extends WelpFluentTestNG {

    public void title_of_bing_should_contain_search_query_name() {
        System.out.println("yay");

        goTo("127.0.0.1");
        fill("#sb_form_q").with("FluentLenium");
        submit("#sb_form_go");
        assertThat(title()).contains("FluentLenium");
    }

}
