package expansion;

import config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver get() {
        String browserName = System.getenv().get("browser");
        if (browserName == null) {
            browserName = "chrome";
        }

        WebDriver driver;
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "yandex":
                driver = createYandexDriver();
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfig.WAIT_SECONDS_TIMEOUT));
        return driver;
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", String.format("%s/%s", System.getenv("BROWSER_DRIVERS"),
                System.getenv("YANDEX_BROWSER_DRIVER_FILENAME")));
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return new ChromeDriver(options);
    }

}
