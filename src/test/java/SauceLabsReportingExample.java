import com.saucelabs.saucerest.SauceREST;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SauceLabsReportingExample {
    public static final String USERNAME = "daulet";
    public static final String KEY = "f098ca38-48d4-4f88-9667-acd7336db9c4";
    public static final String HUB_URL = "https://" + USERNAME + ":" + KEY + "@ondemand.saucelabs.com:443/wd/hub";
    private WebDriver driver;
    private DesiredCapabilities capabilities;
    private URL url;

    @BeforeClass
    public void setup() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        url = new URL(HUB_URL);

    }

    @BeforeMethod
    protected void startTest(Method method) {
        String testName = method.getName();
        capabilities.setCapability("name", testName);
        driver = new RemoteWebDriver(url, capabilities);
    }

    @Test
    public void test1() {
        driver.get("https://google.com");
        Assert.fail();
    }

    @Test
    public void test2() {
        driver.get("https://google.com");
    }

    @AfterMethod
    public void fetchMostRecentTestResult(ITestResult result) {
        Map data = new HashMap();

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                data.put("passed", true);
                break;
            case ITestResult.FAILURE:
                data.put("passed", false);
                break;
            default:

        }

        SauceREST client = new SauceREST(USERNAME, KEY);
        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        client.updateJobInfo(sessionId, data);

        driver.quit();
    }
}