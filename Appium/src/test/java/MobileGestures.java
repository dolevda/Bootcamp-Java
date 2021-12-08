import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.logging.Level;

import static org.testng.AssertJUnit.assertEquals;


public class MobileGestures {

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
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        driver.findElement(By.xpath("//*[@text='Date Widgets']")).click();
        driver.findElement(By.xpath("//*[@text='2. Inline']")).click();

        AndroidElement startLocationHour=driver.findElement(By.xpath("//*[@contentDescription='12']"));
        AndroidElement endLocationHour=driver.findElement(By.xpath("//*[@contentDescription='9']"));


        PointOption startRectHour =PointOption.point(startLocationHour.getRect().x,startLocationHour.getRect().y);
        PointOption endRectHour =PointOption.point(endLocationHour.getRect().x,endLocationHour.getRect().y);

        swipe(startRectHour,endRectHour);

        AndroidElement startLocationMinutes=driver.findElement(By.xpath("//*[@contentDescription='15']"));
        AndroidElement endLocationMinutes=driver.findElement(By.xpath("//*[@contentDescription='45']"));


        PointOption startRectMinutes =PointOption.point(startLocationMinutes.getRect().x,startLocationMinutes.getRect().y);
        PointOption endRectMinutes =PointOption.point(endLocationMinutes.getRect().x,endLocationMinutes.getRect().y);

        swipe(endRectMinutes,startRectMinutes);

        String hour = driver.findElementById("hours").getText();
        String minutes = driver.findElementById("minutes").getText();
        assertEquals("9:45",hour + ":" + minutes);

    }

    public void swipe(PointOption start, PointOption end) {
        final int ANIMATION_TIME = 200;

        final int PRESS_TIME = 200;
        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        try {
            new TouchAction(driver)
                    .press(start)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(end)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }

    }


    @Test
    public void test02() {
        driver.findElement(By.xpath("//*[@text='Views']")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Expandable Lists']")));

        driver.findElement(By.xpath("//*[@text='Expandable Lists']")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='1. Custom Adapter']")));

        driver.findElement(By.xpath("//*[@text='1. Custom Adapter']")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='People Names']")));
        driver.findElement(By.xpath("//*[@text='People Names']")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Sample action']")));


        driver.findElement(By.xpath("//*[@text='Sample action']")).click();


        // Option 1 - Press
        MobileElement elem = (driver.findElement(By.xpath("//*[@text='People")));

        TouchAction pr = new TouchAction(driver);
        pr.press(new ElementOption().withElement(elem)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).release().perform();
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(), "Sample action");
        driver.findElement(By.xpath("//*[@text='Sample action']")).click();

        // Option 2 - Tap

        TouchAction ta = new TouchAction(driver);
        ta.tap(new TapOptions().withElement(ElementOption.element(elem))).perform();
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(), "Sample action");
        driver.findElement(By.xpath("//*[@text='Sample action']")).click();


        // Option 3 - LongPress (performTouchAction)


        TouchAction longPr = new TouchAction(driver);
        longPr.longPress(new LongPressOptions().withElement(ElementOption.element(elem)).withDuration(Duration.ofSeconds(2))).perform();
        assertEquals(driver.findElement(By.xpath("//*[@text='Sample action']")).getText(), "Sample action");
        driver.findElement(By.xpath("//*[@text='Sample action']")).click();


    }
        @AfterMethod
    public void afterMethod() {
        driver.resetApp();
    }

}