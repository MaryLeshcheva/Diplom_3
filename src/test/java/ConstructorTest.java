import config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    public MainPage mainPage;

    @Before
    public void setup() {
        super.setup(AppConfig.MAIN_URL);

        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход к соусам")
    public void clickOnSaucesSectionButton() {
        mainPage.clickButtonSauce();
        boolean actual = mainPage.isSauceSelected();
        assertTrue("Переход к начинке не произошёл", actual);
    }

    @Test
    @DisplayName("Переход к начинке")
    public void clickOnIngredientsSectionButton() {
        mainPage.clickButtonIngredient();
        boolean actual = mainPage.isIngredientSelected();
        assertTrue("Переход к начинке не произошёл", actual);
    }

    @Test
    @DisplayName("Возвращение к булкам")
    public void clickOnBunsSectionButton() {
        mainPage.clickButtonIngredient();
        mainPage.clickButtonBun();
        boolean actual = mainPage.isBunSelected();
        assertTrue("Переход к булкам не произошёл", actual);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }
}
