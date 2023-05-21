package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private static final String selectedButtonClassName = "current";
    private final By buttonEnterAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By buttonBun = By.xpath(".//span[text()='Булки']/..");
    private final By buttonSauce = By.xpath(".//span[text()='Соусы']/..");
    private final By buttonIngredient = By.xpath(".//span[text()='Начинки']/..");
    private final By title = By.xpath(".//h1[text() = 'Соберите бургер']");
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonEnterAccount() {
        driver.findElement(buttonEnterAccount).click();
    }
    public void clickButtonBun() {
        driver.findElement(buttonBun).click();
    }
    public void clickButtonSauce() {
        driver.findElement(buttonSauce).click();
    }
    public void clickButtonIngredient() {
        driver.findElement(buttonIngredient).click();
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    public boolean isBunSelected() {
        return driver.findElement(buttonBun).getAttribute("class").contains(selectedButtonClassName);
    }

    public boolean isSauceSelected() {
        return driver.findElement(buttonSauce).getAttribute("class").contains(selectedButtonClassName);
    }

    public boolean isIngredientSelected() {
        return driver.findElement(buttonIngredient).getAttribute("class").contains(selectedButtonClassName);
    }
}
