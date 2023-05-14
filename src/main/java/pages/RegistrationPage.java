package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private final By inputName = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final By inputEmail = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final By inputPassword = By.xpath(".//input[@name='Пароль']");
    private final By buttonRegistration = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By buttonEnter = By.xpath(".//a[text() = 'Войти']");
    private final By messageIncorrectPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    private final WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickButtonEnter() {
        driver.findElement(buttonEnter).click();
    }
    public boolean isMessageIncorrectPasswordDisplayed() {
        return driver.findElement(messageIncorrectPassword).isDisplayed();
    }
    public void register(String name, String email, String password) {
        setInputName(name);
        setInputEmail(email);
        setInputPassword(password);
        clickButtonRegistration();
    }
    private void setInputName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }
    private void setInputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }
    private void setInputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }
    private void clickButtonRegistration() {
        driver.findElement(buttonRegistration).click();
    }
}

