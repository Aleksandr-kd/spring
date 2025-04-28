package factory;

import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import factory.settings.FirefoxSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser");

    public WebDriver getDriver() {
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
