import config.AppConfig;
import expansion.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.MainPage;

import java.time.Duration;
import static org.junit.Assert.assertNotEquals;

public class ConstructorTest {
    private WebDriver driver;
    public MainPage mainPage;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        driver.navigate().to(AppConfig.MAIN_URL);

        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход к соусам")
    public void clickOnSousesSectionButtonAutoScroll() {
        double oldScrollTop = mainPage.getMenuContainerScrollTop();
        mainPage.clickButtonSouse();
        waitForScrollChange(oldScrollTop);
        double newScrollTop = mainPage.getMenuContainerScrollTop();

        assertNotEquals("Автоскролл к соусам не произошёл", oldScrollTop, newScrollTop);
    }

    @Test
    @DisplayName("Переход к начинке")
    public void clickOnIngredientsSectionButtonAutoScroll() {
        double oldScrollTop = mainPage.getMenuContainerScrollTop();
        mainPage.clickButtonSouse();
        waitForScrollChange(oldScrollTop);
        double newScrollTop = mainPage.getMenuContainerScrollTop();

        assertNotEquals("Автоскролл к ингредиентам не произошёл", oldScrollTop, newScrollTop);
    }

    @Test
    @DisplayName("Возвращение к булкам")
    public void clickOnBunsSectionButtonAutoScroll() {
        double initialScrollTop = mainPage.getMenuContainerScrollTop();
        mainPage.clickButtonIngredient();
        waitForScrollChange(initialScrollTop);
        double oldScrollTop = mainPage.getMenuContainerScrollTop();
        mainPage.clickButtonBun();
        waitForScrollChange(oldScrollTop);
        double newScrollTop = mainPage.getMenuContainerScrollTop();

        assertNotEquals("Автоскролл к булкам не произошёл", oldScrollTop, newScrollTop);
    }

    @After
    public void tearDown() {
        System.out.print("Test is closed");
        driver.quit();
    }

    private void waitForScrollChange(double scrollTop) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1));

        try {
            wait.until(driver -> mainPage.getMenuContainerScrollTop() != scrollTop);
        } catch (TimeoutException exception) {

        }
    }
}
