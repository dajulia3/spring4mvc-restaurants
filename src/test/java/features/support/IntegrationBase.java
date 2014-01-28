package features.support;

import org.fluentlenium.adapter.FluentTestNg;
import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.action.FillConstructor;
import org.fluentlenium.core.action.FillSelectConstructor;
import org.fluentlenium.core.action.FluentDefaultActions;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.filter.Filter;
import org.fluentlenium.core.wait.FluentWait;
import org.junit.AfterClass;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import welp.Application;
import welp.config.TestConfiguration;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = {TestConfiguration.class})
@SharedDriver(type = SharedDriver.SharedType.ONCE)
@ActiveProfiles("test")
public abstract class IntegrationBase extends AbstractTestNGSpringContextTests {
    private FluentTestNg fluentTestNGDelegate;

    public IntegrationBase(){
        super();

        fluentTestNGDelegate = new FluentTestNg(){};
    }
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
        fluentTestNGDelegate.getDriver().close();
    }

    // Delegate a bunch of stuff to our FluentTestNG so that we can still write nice, readable tests

    @BeforeClass
    public void beforeClass(){
        fluentTestNGDelegate.beforeClass();
    }

    @AfterClass
    public void afterClass() {
        fluentTestNGDelegate.afterClass();
    }

    @AfterMethod
    public void afterMethod() {
        fluentTestNGDelegate.afterMethod();
    }

    @BeforeMethod
    public void beforeMethod() {
        fluentTestNGDelegate.beforeMethod();
    }

    public Fluent withDefaultUrl(String baseUrl) {
        return fluentTestNGDelegate.withDefaultUrl(baseUrl);
    }

    public Fluent withDefaultPageWait(long l, TimeUnit timeUnit) {
        return fluentTestNGDelegate.withDefaultPageWait(l, timeUnit);
    }

    public Fluent withDefaultSearchWait(long l, TimeUnit timeUnit) {
        return fluentTestNGDelegate.withDefaultSearchWait(l, timeUnit);
    }

    public Fluent takeScreenShot() {
        return fluentTestNGDelegate.takeScreenShot();
    }

    public Fluent takeScreenShot(String fileName) {
        return fluentTestNGDelegate.takeScreenShot(fileName);
    }

    public WebDriver getDriver() {
        return fluentTestNGDelegate.getDriver();
    }

    public String getBaseUrl() {
        return fluentTestNGDelegate.getBaseUrl();
    }

    public FluentWait await() {
        return fluentTestNGDelegate.await();
    }

    public String title() {
        return fluentTestNGDelegate.title();
    }

    public Set<Cookie> getCookies() {
        return fluentTestNGDelegate.getCookies();
    }

    public Cookie getCookie(String name) {
        return fluentTestNGDelegate.getCookie(name);
    }

    public String url() {
        return fluentTestNGDelegate.url();
    }

    public String pageSource() {
        return fluentTestNGDelegate.pageSource();
    }

    public <P extends FluentPage> P goTo(P page) {
        return fluentTestNGDelegate.goTo(page);
    }

    public Fluent goTo(String url) {
        return fluentTestNGDelegate.goTo(url);
    }

    public Fluent executeScript(String script) {
        return fluentTestNGDelegate.executeScript(script);
    }

    public FluentList<FluentWebElement> $(String name, Filter... filters) {
        return fluentTestNGDelegate.$(name, filters);
    }

    public FluentWebElement $(String name, Integer number, Filter... filters) {
        return fluentTestNGDelegate.$(name, number, filters);
    }

    public FluentList<FluentWebElement> find(String name, Filter... filters) {
        return fluentTestNGDelegate.find(name, filters);
    }

    public FluentWebElement find(String name, Integer number, Filter... filters) {
        return fluentTestNGDelegate.find(name, number, filters);
    }

    public FluentWebElement findFirst(String name, Filter... filters) {
        return fluentTestNGDelegate.findFirst(name, filters);
    }

    public FillConstructor fill(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.fill(cssSelector, filters);
    }

    public FillConstructor fill(FluentDefaultActions list, Filter... filters) {
        return fluentTestNGDelegate.fill(list, filters);
    }

    public FillSelectConstructor fillSelect(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.fillSelect(cssSelector, filters);
    }

    public Fluent click(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.click(cssSelector, filters);
    }

    public Fluent clear(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.clear(cssSelector, filters);
    }

    public Fluent submit(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.submit(cssSelector, filters);
    }

    public List<String> text(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.text(cssSelector, filters);
    }

    public List<String> value(String cssSelector, Filter... filters) {
        return fluentTestNGDelegate.value(cssSelector, filters);
    }

    public Fluent click(FluentDefaultActions fluentObject) {
        return fluentTestNGDelegate.click(fluentObject);
    }

    public Fluent clear(FluentList<FluentWebElement> fluentObject) {
        return fluentTestNGDelegate.clear(fluentObject);
    }

    public Fluent clear(FluentWebElement fluentObject) {
        return fluentTestNGDelegate.clear(fluentObject);
    }

    public Fluent submit(FluentDefaultActions fluentObject) {
        return fluentTestNGDelegate.submit(fluentObject);
    }

    public Fluent switchTo(FluentWebElement element) {
        return fluentTestNGDelegate.switchTo(element);
    }

    public Fluent switchTo() {
        return fluentTestNGDelegate.switchTo();
    }

    public Fluent switchToDefault() {
        return fluentTestNGDelegate.switchToDefault();
    }
}
