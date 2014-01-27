package features.support;

import org.springframework.boot.SpringApplication;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import welp.Application;

public class TestSupport {

//    public WebDriver webDriver = new ChromeDriver();
//
//    @Override
//    public WebDriver getDefaultDriver() {
//        return webDriver;
//    }

    @BeforeSuite
    public void startServer() {
        Thread appServerThread = new Thread() {
          public void run() {
              SpringApplication.run(Application.class, null);
          }
        };

        appServerThread.start();
    }

    @AfterMethod
    public void closeBrowser() {
    }
}
