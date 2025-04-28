package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateUtils {
    private final WebDriverWait wait;
    private final WebDriver driver;


    public DateUtils(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setDate(WebElement element, String date) {
        String day = date.substring(0, 2);
        String month = date.substring(3, 5);
        String year = date.substring(6);

        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        element.clear();

        new Actions(driver)
                .sendKeys(element, day)
                .sendKeys(month)
                .sendKeys(year)
                .sendKeys(Keys.TAB)
                .perform();
    }

    public String dateFormatter(String data) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return LocalDate.parse(data, inputFormat)
                .format(outputFormat);
    }
}
