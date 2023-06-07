import expansion.WebDriverFactory;
import generators.UserGenerator;
import io.restassured.response.ValidatableResponse;
import models.User;
import org.openqa.selenium.WebDriver;
import web_api_clients.UserClient;

public abstract class BaseTest {

    protected WebDriver driver;
    protected final UserClient userClient = new UserClient();
    protected final User user = UserGenerator.createDefault();
    private String accessToken;

    protected void setup(String url) {
        driver = WebDriverFactory.get();
        if (!url.isEmpty()) {
            driver.navigate().to(url);
        }
    }

    protected void tearDown() {
        System.out.print("Test is closed");
        driver.quit();
    }

    protected void registerUser() {
        ValidatableResponse responseCreate = userClient.create(user);

        accessToken = responseCreate.extract().path("accessToken");
    }

    protected void deleteUser() {
        userClient.delete(accessToken);
    }
}
