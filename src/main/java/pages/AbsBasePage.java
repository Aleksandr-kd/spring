package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class AbsBasePage {

    protected WebDriver driver = null;
    private String path = "";
    private final String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(baseUrl + path);
    }
}
