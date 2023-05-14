import config.AppConfig;
import expansion.WebDriverFactory;
import generators.UserGenerator;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.NavigationPanel;
import pages.ProfilePage;
import webApiClients.UserClient;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;


public class LogoutTest {

    private WebDriver driver;
    private ProfilePage profilePage;
    private NavigationPanel navigationPanel;
    private UserClient userClient;
    private LoginPage loginPage;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverFactory.get();
        driver.navigate().to(AppConfig.LOGIN_URL);

        user = UserGenerator.createDefault();
        userClient = new UserClient();
        ValidatableResponse responseCreate = userClient.create(user);
        accessToken = responseCreate.extract().path("accessToken");

        profilePage = new ProfilePage(driver);
        navigationPanel = new NavigationPanel(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void logout() {
        loginPage.login(user.getEmail(), user.getPassword());
        navigationPanel.clickButtonPersonalAccount();
        profilePage.clickButtonExit();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось выйти из личного кабинета", actual);
    }

    @After
    public void tearDown() {
        System.out.print("Test is closed");
        driver.quit();

        userClient.delete(accessToken);
    }
}
