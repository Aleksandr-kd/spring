package two.onetwo.pages.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import two.onetwo.pages.utils.DateUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Component
@Scope("prototype")
public class RegistrationFormPage extends AbsBasePage {
    private final DateUtils dateUtils;

    @Autowired
    public RegistrationFormPage(@Qualifier("testDriver") WebDriver driver) {
        super(driver, "/form.html");
        this.dateUtils = new DateUtils(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "confirm_password")
    private WebElement confirmPassword;

    @FindBy(id = "birthdate")
    private WebElement birthDate;

    @FindBy(id = "language_level")
    private WebElement languageLevel;

    @FindBy(css = "[id='output']")
    private WebElement output;

    @FindBy(css = "form#registrationForm input[type='submit']")
    private WebElement registrationForm;

    @Step("Ввести имя пользователя: {nameRegistration}")
    public void setName(String nameRegistration) {
        name.click();
        name.sendKeys(nameRegistration);
    }

    @Step("Ввести email пользователя")
    public void setEmail(String emailRegistration) {
        email.sendKeys(emailRegistration);
    }

    @Step("Ввести пароль пользователя")
    public void setPassword(String passwordRegistration) {
        password.sendKeys(passwordRegistration);
    }

    @Step("Повторить пароль пользователя")
    public void setPasswordRepeat(String passwordTwoRegistration) {
        confirmPassword.sendKeys(passwordTwoRegistration);
    }

    @Step("Установить дату рождения пользователя")
    public void setBirthDate(String birthdateUser) {
        dateUtils.setDate(birthDate, birthdateUser);
    }

    @Step("Установить уровень языка пользователя")
    public void setLanguageLevel(String setLanguageUser) {
        languageLevel.click();
        languageLevel.sendKeys(setLanguageUser);
        languageLevel.click();
    }

    @Step("Зарегистрировать пользователя")
    public void userRegistration() {
        registrationForm.click();
    }

    @Step("Получение текста после регистрации")
    public String getTextRegistration() {
        return output.getText();
    }

    @Step("Форматирование даты")
    public String dataRegistration(String data) {
        return dateUtils.dateFormatter(data);
    }

    @Step("Отображение alert ошибки")
    public boolean isPasswordErrorDisplayed() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Закрыть alert")
    public void closeAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Step("Текст alert ошибки")
    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Step("Заполнение формы регистрации")
    public void formRegistration(String name, String email, String password, String passwordRepeat
            , String birthDate, String language) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setPasswordRepeat(passwordRepeat);
        setBirthDate(birthDate);
        setLanguageLevel(language);
    }

    @Step("Проверка регистрации")
    public void checkRegistration(String title) {
        assertThat(getTextRegistration().replace("\n", " "))
                .isEqualTo(title.replace("\n", " "));
    }

    @Step("Проверка ввода повторного пароля")
    public void checkRegistrationFalse(String title) {
        assertThat(getAlertText())
                .isEqualTo(title);
    }

    @Step("Отображения уведомления об ошибки")
    public void passwordErrorDisplayed() {
        assertThat(isPasswordErrorDisplayed())
                .isFalse();
    }
}