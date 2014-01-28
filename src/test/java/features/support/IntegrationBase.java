package features.support;

import org.fluentlenium.adapter.util.SharedDriver;
import org.junit.AfterClass;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import welp.Application;
import welp.config.TestConfiguration;

@ContextConfiguration(classes = {TestConfiguration.class})
@SharedDriver(type = SharedDriver.SharedType.ONCE)
@ActiveProfiles("test")
public abstract class IntegrationBase extends AbstractTestNGSpringContextTests {

    @BeforeClass
    public void before() {
        applicationContext = Application.run();
    }

    @AfterClass
    public void shutdownApp() {
        SpringApplication.exit(applicationContext);
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().close();
    }

}
