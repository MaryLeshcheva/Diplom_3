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
import pages.*;
import webApiClients.UserClient;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private WebDriver driver;
    private NavigationPanel navigationPanel;
    public RegistrationPage registrationPage;
    public MainPage mainPage;
    private UserClient userClient;
    private ResetPasswordPage resetPasswordPage;
    private LoginPage loginPage;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverFactory.get();

        user = UserGenerator.createDefault();
        userClient = new UserClient();
        ValidatableResponse responseCreate = userClient.create(user);
        accessToken = responseCreate.extract().path("accessToken");

        registrationPage = new RegistrationPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
        navigationPanel = new NavigationPanel(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в аккаунт на главной странице")
    public void LoginWithButtonEnterAccountOnMainPage() {
        driver.navigate().to(AppConfig.MAIN_URL);
        mainPage.clickButtonEnterAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @Test
    @DisplayName("Авторизация по кнопке Личный кабинет")
    public void LoginWithButtonPersonalAccount() {
        driver.navigate().to(AppConfig.MAIN_URL);
        navigationPanel.clickButtonPersonalAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в форме регистрации")
    public void LoginWithButtonEnterOnRegistrationForm() {
        driver.navigate().to(AppConfig.REGISTRATION_URL);
        registrationPage.clickButtonEnter();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void LoginWithResetPasswordForm() {
        driver.navigate().to(AppConfig.RESET_PASSWORD_URL);
        resetPasswordPage.clickButtonEnter();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @After
    public void tearDown() {
        System.out.print("Test is closed");
        driver.quit();

        userClient.delete(accessToken);
    }
}
