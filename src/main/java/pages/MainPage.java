package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {

    private final By buttonEnterAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By buttonBun = By.xpath(".//span[text()='Булки']");
    private final By buttonSouse = By.xpath(".//span[text()='Соусы']");
    private final By buttonIngredient = By.xpath(".//span[text()='Начинки']");
    private final By menuContainerIngredients = By.xpath(".//div[starts-with(@class, 'BurgerIngredients_ingredients__menuContainer__')]");
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
    public void clickButtonSouse() {
        driver.findElement(buttonSouse).click();
    }
    public void clickButtonIngredient() {
        driver.findElement(buttonIngredient).click();
    }

    public double getMenuContainerScrollTop() {
        return getScrollTop(driver.findElement((menuContainerIngredients)));
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(title).isDisplayed();
    }

    private double getScrollTop(WebElement element) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor)driver);

        try {
            return  (double)javascriptExecutor.executeScript("return arguments[0].scrollTop;", element);
        } catch (ClassCastException exception) {
        }

        return 0;
    }
}
