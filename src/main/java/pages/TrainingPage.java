package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;


public class TrainingPage extends AbsBasePage {

    public TrainingPage(WebDriver driver) {
        super(driver, "/training.html");
    }

    @FindBy(tagName = "h1")
    private WebElement pageHeader;

    @FindBy(id = "textInput")
    private WebElement textInput;

    @FindBy(id = "openModalBtn")
    private WebElement openModalButton;

    @FindBy(id = "closeModal")
    private WebElement closeModalButton;

    @FindBy(css = "div.modal-content p")
    private WebElement textModalContent;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(css = "button[type='submit']")
    private WebElement send;

    @FindBy(id = "messageBox")
    private WebElement messageBox;

    public String getSimulatorPageText() {
        return pageHeader.getText();
    }

    @Step("Вводим в поле ввода текст ОТУС")
    public String setTextInput(String text) {
        textInput.sendKeys(text);
        return text;
    }

    @Step("Открыть модальное окно")
    public void openModal() {
        openModalButton.click();
    }

    @Step("Закрыть модальное окно")
    public void closeModal() {
        closeModalButton.click();
    }

    @Step("Текст модального окна")
    public String getTextModal() {
        return textModalContent.getText();
    }

    @Step("Ввод имени")
    public void enterName(String textName) {
        name.sendKeys(textName);
    }

    @Step("Ввод почты")
    public void enterEmail(String textEmail) {
        email.sendKeys(textEmail);
    }

    @Step("Нажать отправить")
    public void clickSend() {
        send.click();
    }

    @Step("Получение текста на зеленом поле")
    public String getTextGreen() {
        return messageBox.getText();
    }

    @Step("Ввести имя и почту")
    public void openingModalWindow(String name, String email) {
        enterName(name);
        enterEmail(email);
        clickSend();
    }

    @Step("Проверка работы модального окна")
    public void openingModalWindow(String text) {
        assertThat(getTextModal())
                .isEqualTo(text);
    }

    @Step("Проверка сообщения на залёном фоне")
    public void messageGreenBackground(String text) {
        assertThat(getTextGreen())
                .isEqualTo(text);
    }

    @Step("Проверка открытия нужной страницы")
    public void pageTitleShouldBeSameAs(String title) {
        assertThat(getSimulatorPageText())
                .isEqualTo(title);
    }

    @Step("Проверка ввода текст")
    public void textInputFieldMustBeSameAs(String text) {
        assertThat(setTextInput(text))
                .isEqualTo(text);
    }
}