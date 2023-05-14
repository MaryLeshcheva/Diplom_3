package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPanel {
    private final By buttonPersonalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    private final By buttonConstructor = By.xpath(".//p[text()='Конструктор']");
    private final By buttonLogo = By.className("AppHeader_header__logo__2D0X2");
    private final WebDriver driver;
    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }
    public void clickButtonPersonalAccount() {
        driver.findElement(buttonPersonalAccount).click();
    }
    public void clickButtonConstructor() {
        driver.findElement(buttonConstructor).click();
    }
    public void clickButtonLogo() {
        driver.findElement(buttonLogo).click();
    }
}
