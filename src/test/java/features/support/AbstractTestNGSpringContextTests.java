package features.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fluentlenium.adapter.FluentTestNg;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Abstract base test class which integrates the <em>Spring TestContext Framework</em>
 * with explicit {@link org.springframework.context.ApplicationContext} testing support in a <strong>TestNG</strong>
 * environment.
 *
 * <p>Concrete subclasses:
 * <ul>
 * <li>Typically declare a class-level {@link org.springframework.test.context.ContextConfiguration
 * &#064;ContextConfiguration} annotation to configure the {@link org.springframework.context.ApplicationContext
 * application context} {@link org.springframework.test.context.ContextConfiguration#locations() resource locations}
 * or {@link org.springframework.test.context.ContextConfiguration#classes() annotated classes}. <em>If your test
 * does not need to load an application context, you may choose to omit the
 * {@link org.springframework.test.context.ContextConfiguration &#064;ContextConfiguration} declaration and to
 * configure the appropriate
 * {@link org.springframework.test.context.TestExecutionListener TestExecutionListeners}
 * manually.</em></li>
 * <li>Must have constructors which either implicitly or explicitly delegate to
 * {@code super();}.</li>
 * </ul>
 *
 * @author Sam Brannen
 * @author Juergen Hoeller
 * @since 2.5
 * @see org.springframework.test.context.TestContext
 * @see org.springframework.test.context.TestContextManager
 * @see org.springframework.test.context.TestExecutionListeners
 * @see org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests
 * @see org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests
 */
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class })
public abstract class AbstractTestNGSpringContextTests extends FluentTestNg implements IHookable, ApplicationContextAware {

    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * The {@link org.springframework.context.ApplicationContext} that was injected into this test instance
     * via {@link #setApplicationContext(org.springframework.context.ApplicationContext)}.
     */
    protected ApplicationContext applicationContext;

    private final TestContextManager testContextManager;

    private Throwable testException;


    /**
     * Construct a new AbstractTestNGSpringContextTests instance and initialize
     * the internal {@link TestContextManager} for the current test.
     */
    public AbstractTestNGSpringContextTests() {
        this.testContextManager = new TestContextManager(getClass());
    }

    /**
     * Set the {@link ApplicationContext} to be used by this test instance,
     * provided via {@link ApplicationContextAware} semantics.
     *
     * @param applicationContext the applicationContext to set
     */
    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Delegates to the configured {@link TestContextManager} to call
     * {@link TestContextManager#beforeTestClass() 'before test class'}
     * callbacks.
     *
     * @throws Exception if a registered TestExecutionListener throws an
     * exception
     */
    @BeforeClass(alwaysRun = true)
    protected void springTestContextBeforeTestClass() throws Exception {
        this.testContextManager.beforeTestClass();
    }

    /**
     * Delegates to the configured {@link TestContextManager} to
     * {@link TestContextManager#prepareTestInstance(Object) prepare} this test
     * instance prior to execution of any individual tests, for example for
     * injecting dependencies, etc.
     *
     * @throws Exception if a registered TestExecutionListener throws an
     * exception
     */
    @BeforeClass(alwaysRun = true, dependsOnMethods = "springTestContextBeforeTestClass")
    protected void springTestContextPrepareTestInstance() throws Exception {
        this.testContextManager.prepareTestInstance(this);
    }

    /**
     * Delegates to the configured {@link TestContextManager} to
     * {@link TestContextManager#beforeTestMethod(Object, java.lang.reflect.Method) pre-process}
     * the test method before the actual test is executed.
     *
     * @param testMethod the test method which is about to be executed.
     * @throws Exception allows all exceptions to propagate.
     */
    @BeforeMethod(alwaysRun = true)
    protected void springTestContextBeforeTestMethod(Method testMethod) throws Exception {
        this.testContextManager.beforeTestMethod(this, testMethod);
    }

    /**
     * Delegates to the {@link org.testng.IHookCallBack#runTestMethod(org.testng.ITestResult) test
     * method} in the supplied {@code callback} to execute the actual test
     * and then tracks the exception thrown during test execution, if any.
     *
     * @see org.testng.IHookable#run(org.testng.IHookCallBack,
     * org.testng.ITestResult)
     */
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);

        Throwable testResultException = testResult.getThrowable();
        if (testResultException instanceof InvocationTargetException) {
            testResultException = ((InvocationTargetException) testResultException).getCause();
        }
        this.testException = testResultException;
    }

    /**
     * Delegates to the configured {@link TestContextManager} to
     * {@link TestContextManager#afterTestMethod(Object, Method, Throwable)
     * post-process} the test method after the actual test has executed.
     *
     * @param testMethod the test method which has just been executed on the
     * test instance
     * @throws Exception allows all exceptions to propagate
     */
    @AfterMethod(alwaysRun = true)
    protected void springTestContextAfterTestMethod(Method testMethod) throws Exception {
        try {
            this.testContextManager.afterTestMethod(this, testMethod, this.testException);
        }
        finally {
            this.testException = null;
        }
    }

    /**
     * Delegates to the configured {@link TestContextManager} to call
     * {@link TestContextManager#afterTestClass() 'after test class'} callbacks.
     *
     * @throws Exception if a registered TestExecutionListener throws an
     * exception
     */
    @AfterClass(alwaysRun = true)
    protected void springTestContextAfterTestClass() throws Exception {
        this.testContextManager.afterTestClass();
    }

}
