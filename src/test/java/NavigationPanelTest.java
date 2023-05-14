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
import pages.MainPage;
import pages.NavigationPanel;
import pages.ProfilePage;
import webApiClients.UserClient;

import static org.junit.Assert.assertTrue;


public class NavigationPanelTest {

    private WebDriver driver;
    private ProfilePage profilePage;
    private NavigationPanel navigationPanel;
    public MainPage mainPage;
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
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход по клику на Конструктор из Личного кабинета")
    public void goToConstructorFromPersonalAccount() {
        loginPage.login(user.getEmail(), user.getPassword());
        navigationPanel.clickButtonPersonalAccount();
        navigationPanel.clickButtonConstructor();
        boolean actual = mainPage.isTitleDisplayed();
        assertTrue("Главная страница не открылась", actual);
    }

    @Test
    @DisplayName("Переход по клику на лого из Личного кабинета")
    public void goToLogoFromPersonalAccount() {
        loginPage.login(user.getEmail(), user.getPassword());
        navigationPanel.clickButtonPersonalAccount();
        navigationPanel.clickButtonLogo();
        boolean actual = mainPage.isTitleDisplayed();
        assertTrue("Главная страница не открылась", actual);
    }

    @Test
    @DisplayName("Переход по клику на кнопку Личный кабинет для авторизированного пользователя")
    public void goToPersonalAccountForAuthorizedUser() {
        loginPage.login(user.getEmail(), user.getPassword());
        navigationPanel.clickButtonPersonalAccount();
        boolean actual = profilePage.isTitleProfile();
        assertTrue("Не удалось зайти в личный кабинет", actual);
    }

    @Test
    @DisplayName("Переход по клику на кнопку Личный кабинет для не авторизированного пользователя")
    public void goToPersonalAccountForUnauthorizedUser() {
        navigationPanel.clickButtonPersonalAccount();
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось открыть страницу авторизации", actual);
    }

    @After
    public void tearDown() {
        System.out.print("Test is closed");
        driver.quit();

        userClient.delete(accessToken);
    }
}
