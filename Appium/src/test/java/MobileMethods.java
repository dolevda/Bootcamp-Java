import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class MobileMethods {
    private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.UDID, "2269b6b83a0d7ece");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.example.android.apis");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }

    @Test
    public void test01() {
        int sum=driver.findElements(By.id("text1")).size();
        try {
            assertEquals(sum,11);
        } catch (Exception e) {
            System.out.println("No 11 elements!!");
        }
    }
    @Test
    public void test02(){
        System.out.println("Width:" + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getWidth());
        System.out.println("Height:" + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getHeight());
        System.out.println("X:" + driver.findElement(By.xpath("//*[@text='Content']")).getRect().getX());
        System.out.println("Y:" + driver.findElement(By.xpath("//*[@text='Content']")).getRect());

    }

    @Test
    public void test03(){
        System.out.println(driver.currentActivity());
        System.out.println(driver.getDeviceTime());

    }
    @Test
    public void test04(){
        try {
            assertTrue(driver.isAppInstalled("com.example.android.apis"));
        } catch (Exception e) {
            System.out.println("Application isn't Installed!");
        }
    }


    @Test
    public void test05() {
        if (driver.getOrientation().equals("PORTRAIT"))
            driver.rotate(ScreenOrientation.LANDSCAPE);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Test
    public void test06() throws IOException {
        driver.openNotifications();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("C:/Automation/TestAuto/screenshotFile"+".png"));
        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
        File screenshotFileHome = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("C:/Automation/TestAuto/screenshotFileHome"+".png"));



    }

    @Test
    public void test07() {
        if(driver.isDeviceLocked()==false)
            driver.lockDevice();
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        driver.unlockDevice();

    }
    @Test
    public void test08() {
        String page = driver.getPageSource();
        int count= page.split("ListView").length;

        try {
            assertTrue(count == 4);
        }
        catch (AssertionError e) {
            System.out.println("ListView dose not show 4 times!!");

        }
    }


        @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}