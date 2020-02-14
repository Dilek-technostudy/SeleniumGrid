import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SaurceLabsReportingResults {
    public static final String USERNAME = "daulet";
    public static final String KEY = "f098ca38-48d4-4f88-9667-acd7336db9c4";
    private WebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("safari");
        cap.setPlatform( Platform.MAC );
        cap.setCapability("name", "Web Driver demo Test");
        URL url = new URL( "https://" + USERNAME + ":" + KEY + "@ondemand.saucelabs.com:443/wd/hub" );
        driver = new RemoteWebDriver(url, cap);

    }

    @Test
    public void test() {
        driver.get("https://google.com");

        SauceREST client = new SauceREST(USERNAME, KEY);
        Map<String, Object> updates = new HashMap<>();
        updates.put("passed", true);
        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        client.updateJobInfo(sessionId, updates);

    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}