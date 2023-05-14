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
import pages.RegistrationPage;
import webApiClients.UserClient;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class RegistrationTest {
    private WebDriver driver;
    public RegistrationPage registrationPage;
    private LoginPage loginPage;
    private User user;

    @Before
    public void setUp() {
        driver = WebDriverFactory.get();
        driver.navigate().to(AppConfig.REGISTRATION_URL);

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        user = UserGenerator.createDefault();
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void registrationUser() {
        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Пользователь зарегистрирован", actual);

        UserClient userClient = new UserClient();
        ValidatableResponse responseLogin = userClient.login(user);
        String accessToken = responseLogin.extract().path("accessToken");
        userClient.delete(accessToken);
    }

    @Test
    @DisplayName("Регистрация пользователя c невалидным паролем")
    public void registrationUserWithInvalidPassword() {
        registrationPage.register(user.getName(), user.getEmail(), "12345");
        boolean actual = registrationPage.isMessageIncorrectPasswordDisplayed();
        assertTrue("Пользователь зарегистрирован", actual);
    }

    @After
    public void tearDown() {
        System.out.print("Test is closed");
        driver.quit();
    }
}
