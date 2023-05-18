import config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import pages.LoginPage;
import pages.NavigationPanel;
import pages.ProfilePage;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;


public class LogoutTest extends BaseTest {

    private ProfilePage profilePage;
    private NavigationPanel navigationPanel;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        super.setup(AppConfig.LOGIN_URL);

        registerUser();

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
        super.tearDown();

        deleteUser();
    }
}
