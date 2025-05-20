package two.onetwo.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbsBasePage {
    protected WebDriver driver = null;
    private String path = "";
    private final String baseUrl = System.getProperty("base.url");

    @Autowired
    public AbsBasePage(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
    }

    @Step("Открытие страницы")
    public void open() {
        driver.get(baseUrl + path);
    }
}
