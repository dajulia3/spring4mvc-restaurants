package features.support;

import org.fluentlenium.adapter.FluentTestNg;
import org.testng.annotations.AfterMethod;

public class WelpFluentTestNG extends FluentTestNg {
    @AfterMethod
    public void closeDriver() {
        getDriver().close();
    }
}
