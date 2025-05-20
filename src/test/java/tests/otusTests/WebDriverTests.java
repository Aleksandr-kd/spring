package tests.otusTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import two.TestApplication;
import two.onetwo.pages.pages.TrainingPage;


@SpringBootTest(classes = TestApplication.class)
public class WebDriverTests {

    @BeforeEach
    void setUp() {
        trainingPage.open();
    }

    @Autowired
    private TrainingPage trainingPage;

    @Test
    @Tag("test")
    @DisplayName("Проверка открытия верной страницы")
    public void testOne() {
        String enterText = "ОТУС";
        String title = "Тренажёр для оттачивания навыков работы с Selenium";

        trainingPage.pageTitleShouldBeSameAs(title);
        trainingPage.textInputFieldMustBeSameAs(enterText);
    }


    @Test
    @Tag("test")
    @DisplayName("Проверка открытия модального окна")
    public void testTwo() {
        String text = "Вы открыли модальное окно. Нажмите на крестик или в любое место вне окна, чтобы закрыть его.";

        trainingPage.openModal();
        trainingPage.openingModalWindow(text);
        trainingPage.closeModal();
    }

    @Test
    @Tag("test")
    @DisplayName("Проверка формы")
    public void testThree() {
        String name = "Александ";
        String email = "alexander@mail.ru";
        String text = String.format("Форма отправлена с именем: %s и email: %s", name, email);

        trainingPage.openingModalWindow(name, email);
        trainingPage.messageGreenBackground(text);
    }
}