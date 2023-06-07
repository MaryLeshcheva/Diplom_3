import config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pages.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private NavigationPanel navigationPanel;
    public RegistrationPage registrationPage;
    public MainPage mainPage;
    private ResetPasswordPage resetPasswordPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        super.setup("");

        registerUser();

        registrationPage = new RegistrationPage(driver);
        resetPasswordPage = new ResetPasswordPage(driver);
        navigationPanel = new NavigationPanel(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в аккаунт на главной странице")
    public void loginWithButtonEnterAccountOnMainPage() {
        driver.navigate().to(AppConfig.MAIN_URL);
        mainPage.clickButtonEnterAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @Test
    @DisplayName("Авторизация по кнопке Личный кабинет")
    public void loginWithButtonPersonalAccount() {
        driver.navigate().to(AppConfig.MAIN_URL);
        navigationPanel.clickButtonPersonalAccount();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @Test
    @DisplayName("Авторизация по кнопке Войти в форме регистрации")
    public void loginWithButtonEnterOnRegistrationForm() {
        driver.navigate().to(AppConfig.REGISTRATION_URL);
        registrationPage.clickButtonEnter();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @Test
    @DisplayName("Авторизация через кнопку в форме восстановления пароля")
    public void loginWithResetPasswordForm() {
        driver.navigate().to(AppConfig.RESET_PASSWORD_URL);
        resetPasswordPage.clickButtonEnter();
        loginPage.login(user.getEmail(), user.getPassword());
        boolean actual = loginPage.isTitleEnterDisplayed();
        assertTrue("Не удалось авторизоваться", actual);
    }

    @After
    public void tearDown() {
        super.tearDown();

        deleteUser();
    }
}
