package tests.otusTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.TrainingPage;
import tests.TestSetupExtension;


@ExtendWith(TestSetupExtension.class)
public class WebDriverTests {

    @Test
    @Tag("test")
    @DisplayName("Проверка открытия верной страницы")
    public void testOne(WebDriver driver) {
        String enterText = "ОТУС";
        String title = "Тренажёр для оттачивания навыков работы с Selenium";

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();

        trainingPage.pageTitleShouldBeSameAs(title);
        trainingPage.textInputFieldMustBeSameAs(enterText);
    }


    @Test
    @Tag("test")
    @DisplayName("Проверка открытия модального окна")
    public void testTwo(WebDriver driver) {
        String text = "Вы открыли модальное окно. Нажмите на крестик или в любое место вне окна, чтобы закрыть его.";

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();

        trainingPage.openModal();
        trainingPage.openingModalWindow(text);
        trainingPage.closeModal();
    }

    @Test
    @Tag("test")
    @DisplayName("Проверка формы")
    public void testThree(WebDriver driver) {
        String name = "Александ";
        String email = "alexander@mail.ru";
        String text = String.format("Форма отправлена с именем: %s и email: %s", name, email);

        TrainingPage trainingPage = new TrainingPage(driver);
        trainingPage.open();
        trainingPage.openingModalWindow(name, email);
        trainingPage.messageGreenBackground(text);
    }
}