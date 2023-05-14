package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final By inputEmail = By.xpath(".//input[@name='name']");
    private final By inputPassword = By.xpath(".//input[@name='Пароль']");
    private final By buttonEnter = By.xpath(".//button[text()='Войти']");
    private final By titleEnter = By.xpath(".//h2[text() = 'Вход']");

    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isTitleEnterDisplayed() {
        return driver.findElement(titleEnter).isDisplayed();
    }

    public void login(String email, String password) {
        setInputEmail(email);
        setInputPassword(password);
        clickButtonEnter();
    }
    private void setInputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    private void setInputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    private void clickButtonEnter() {
        driver.findElement(buttonEnter).click();
    }
}
