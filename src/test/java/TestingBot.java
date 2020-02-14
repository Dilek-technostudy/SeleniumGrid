
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestingBot {
    public static final String KEY = "f58ee926c31abf4e0ce37902c7c96e31";
    public static final String SECRET = "fd69f7db06f9d9087ef26b034b74fa49";
    public static final String HUB_URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(String browser) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browser);
        cap.setCapability("name", "Selenium Testing Bot Test 1");

        URL url = new URL(HUB_URL);
        driver = new RemoteWebDriver(url, cap);

    }

    @Test
    public void test() {
        driver.get("https://google.com");
        driver.manage().window().maximize();

    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
