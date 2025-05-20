package tests.otusTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import two.TestApplication;
import two.onetwo.pages.pages.RegistrationFormPage;


@SpringBootTest(classes = TestApplication.class)
public class RegistrationFormTests {

    @BeforeEach
    void setUp() {
        registrationFormPage.open();
    }

    @Autowired
    private RegistrationFormPage registrationFormPage;

    @Test
    @Tag("autotest")
    @DisplayName("Проверка регистрации.")
    public void testCorrect() {
        String name = "ОТУС";
        String email = System.getProperty("login");
        String birthDate = "15-12-1995";
        String language = "Продвинутый";
        String password = System.getProperty("password");
        String passwordRepeat = System.getProperty("password.repeat");

        String birthDateFormatted = registrationFormPage.dataRegistration(birthDate);
        String expectedText = String.format("Имя по льзователя: %s Электронная почта: %s Дата рождения: %s " +
                "Уровень языка: advanced", name, email, birthDateFormatted);

        registrationFormPage.formRegistration(
                name,
                email,
                password,
                passwordRepeat,
                birthDate,
                language);
        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistration(expectedText);
    }

    @Test
    @Tag("autotest")
    @DisplayName("Проверка валидации пароля.")
    public void testNotCorrect() {
        String name = "ОТУС";
        String email = System.getProperty("login");
        String birthDate = "15-12-1995";
        String language = "Средний";
        String password = System.getProperty("password");
        String passwordRepeatFalse = System.getProperty("password.false");
        String alertText = "Пароли не совпадают!";

        registrationFormPage.formRegistration(name,
                email,
                password,
                passwordRepeatFalse,
                birthDate,
                language);
        registrationFormPage.userRegistration();
        registrationFormPage.checkRegistrationFalse(alertText);
        registrationFormPage.closeAlert();
        registrationFormPage.passwordErrorDisplayed();
    }
}