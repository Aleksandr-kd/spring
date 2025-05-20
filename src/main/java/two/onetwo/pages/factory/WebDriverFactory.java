package two.onetwo.pages.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import two.onetwo.pages.exceptions.BrowserNotSupportedException;
import two.onetwo.pages.settings.ChromeSettings;
import two.onetwo.pages.settings.FirefoxSettings;

@Configuration
public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    @Bean(name = "testDriver")
    @Primary
    @Scope("prototype")
    public WebDriver getDriver() {
        WebDriver driver = createDriver();
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        return driver;
    }

    public WebDriver createDriver() {
        switch (browserName.toLowerCase()) {
            case "chrome": {
                return new ChromeDriver((ChromeOptions) new ChromeSettings().settings());
            }
            case "firefox": {
                return new FirefoxDriver((FirefoxOptions) new FirefoxSettings().settings());
            }
        }
        throw new BrowserNotSupportedException(browserName);
    }
}
