package tests;

import factory.WebDriverFactory;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import utils.AllureScreenshotUtils;


public class TestSetupExtension implements BeforeEachCallback, AfterTestExecutionCallback, ParameterResolver {

    private final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private final AllureScreenshotUtils screenshotUtils = new AllureScreenshotUtils();

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver driver = new WebDriverFactory().getDriver();
        driverThreadLocal.set(driver);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        WebDriver driver = driverThreadLocal.get();

        if (context.getExecutionException().isPresent()) {
            screenshotUtils.takeScreenshot(driver, "Error: " + context.getDisplayName());
        }

        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext){
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return driverThreadLocal.get();
    }
}
