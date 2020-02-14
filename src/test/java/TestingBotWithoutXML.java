
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class TestingBotWithoutXML {
    public static final String KEY = "f58ee926c31abf4e0ce37902c7c96e31";
    public static final String SECRET = "fd69f7db06f9d9087ef26b034b74fa49";
    public static final String HUB_URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    private WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        caps.setCapability("name", "Android Test 1 - Galaxy S9");
        caps.setCapability("platform", "Android");
        caps.setCapability("version", "9.0");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("deviceName", "Galaxy S9");
        caps.setCapability("platformName", "Android");

        URL url = new URL(HUB_URL);
        driver = new RemoteWebDriver(url, caps);

    }

    @Test
    public void test() {
        driver.get("https://google.com");
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
