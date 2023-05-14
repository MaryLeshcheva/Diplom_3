package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private final By buttonExit = By.xpath(".//button[text()='Выход']");
    private final By TitleProfile = By.xpath(".//a[text()='Профиль']");

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonExit() {
        driver.findElement(buttonExit).click();
    }

    public boolean isTitleProfile() {
        return driver.findElement(TitleProfile).isDisplayed();
    }

}
