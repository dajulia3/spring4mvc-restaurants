package features.support;

import org.fluentlenium.adapter.FluentTestNg;
import org.fluentlenium.core.Fluent;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import welp.Application;

import java.util.concurrent.*;

public class WelpAppFluentTestNG extends FluentTestNg {
    private ConfigurableApplicationContext appContext;

    @BeforeSuite
    public void start() throws InterruptedException, ExecutionException, TimeoutException {
        final Future<ConfigurableApplicationContext> appContextFuture = Executors.newSingleThreadExecutor().submit(
                new Callable<ConfigurableApplicationContext>() {
                    @Override
                    public ConfigurableApplicationContext call() {
                        return SpringApplication.run(Application.class);

                    }
                });

        appContext = appContextFuture.get(90, TimeUnit.SECONDS);
    }

    @AfterSuite
    public void stop() {
        if (appContext != null) {
            appContext.close();
        }
    }

    @AfterMethod
    public void closeDriver() {
        getDriver().close();
    }



    public ConfigurableApplicationContext getAppContext() {
        return appContext;
    }

    public Fluent goToPath(String relativePath) {
        return goTo(getServerUrl() + relativePath);
    }

    public String getServerUrl() {
        final Environment environment = getAppContext().getEnvironment();
//        final Integer port = environment.getProperty("server.port", Integer.class); //Want to configure the server to get this from application.yml, but idk how to yet.
        //maybe I should be using the spring test context, but idk if that's going to work with the fluent test... Will have to look into this!
        final int port = 8080;
        final String contextPath = environment.getProperty("server.contextPath", String.class, "");

        return "http://127.0.0.1:" + port + contextPath;
    }

}
