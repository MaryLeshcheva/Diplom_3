package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {

    private final By buttonEnter = By.className("Auth_link__1fOlj");

    private final WebDriver driver;
    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonEnter() {
        driver.findElement(buttonEnter).click();
    }
}
