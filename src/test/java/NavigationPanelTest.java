import config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pages.LoginPage;
import pages.MainPage;
import pages.NavigationPanel;
import pages.ProfilePage;

import static org.junit.Assert.assertTrue;


public class NavigationPanelTest extends BaseTest {

    private ProfilePage profilePage;
    private NavigationPanel navigationPanel;
    public MainPage mainPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        super.setup(AppConfig.LOGIN_URL);

        registerUser();

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
        super.tearDown();

        deleteUser();
    }
}
