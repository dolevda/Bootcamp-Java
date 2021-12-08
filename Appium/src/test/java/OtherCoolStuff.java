
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
public class OtherCoolStuff {


    private String reportDirectory = "C:\\Automation\\TestAutomation\\Appium\\Reports";
    private String reportFormat = "xml";
    private String testName = "CoolStuffReport";
    protected AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    @BeforeClass
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "RFCR405F5FD");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void test01_success() {
        driver.executeScript("experitest:client.verifyElementFound(\"NATIVE\",\"xpath=//*[@id='text1' and text()='Views']\",0)");
    }
    @Test
    public void test02_fail() {
        driver.executeScript("experitest:client.verifyElementFound(\"NATIVE\",\"xpath=//*[@id='text1' and text()='views']\",0)");
    }
    @Test
    public void test03_newApp() {
        driver.startActivity(new Activity("com.experitest.ExperiBank", ".LoginActivity"));
    }
}
