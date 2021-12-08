package lesson18_14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Electron {

    private WebDriver driver;
    private ChromeOptions opt;
    private DesiredCapabilities capabilities;

    @BeforeClass
    public void startSession() {
        System.setProperty("webdriver.chrome.driver", "C:/Automation/electrondriver.exe");
        opt = new ChromeOptions();
        opt.setBinary("C:/Automation/Electron API Demos-win32-ia32/Electron API Demos.exe");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("chromeOptions", opt);
        capabilities.setBrowserName("chrome");
        driver = new ChromeDriver(capabilities);
    }

    @Test()
    public void test01() {
        driver.findElement(By.id("button-dialogs")).click();
        driver.findElement(By.id("button-windows")).click();
        driver.findElement(By.id("button-ipc")).click();
        driver.findElement(By.id("button-crash-hang")).click();
        driver.findElement(By.id("button-protocol")).click();
        driver.findElement(By.id("button-menus")).click();
    }

    @AfterClass
    public void endSession() {
        driver.quit();

    }
}