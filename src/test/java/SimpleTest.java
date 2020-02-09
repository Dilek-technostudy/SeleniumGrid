import org.openqa.selenium.Platform;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
public class SimpleTest {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(String browser) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browser);

//        cap.setPlatform(Platform.WINDOWS);
        URL url = new URL("http://localhost:4444/wd/hub");

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
