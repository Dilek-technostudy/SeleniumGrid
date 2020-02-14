
import com.testingbot.testingbotrest.TestingbotREST;
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

public class TestingBotReportingExample {
    public static final String KEY = "f58ee926c31abf4e0ce37902c7c96e31";;
    public static final String SECRET ="fd69f7db06f9d9087ef26b034b74fa49";
    public static final String HUB_URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
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
                data.put("success", "1");
                break;
            case ITestResult.FAILURE:
                data.put("success", "0");
                break;
            default:

        }

        String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
        TestingbotREST r = new TestingbotREST(KEY, SECRET);

        r.updateTest(sessionId, data);
        driver.quit();
    }


}