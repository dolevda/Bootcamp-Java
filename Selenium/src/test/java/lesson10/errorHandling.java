package lesson10;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class errorHandling {
    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");

    }

    @Test
    public void test01() {
        try {
            driver.findElement(By.id("btn")).click();
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            driver.findElement(By.id("checkbox"));
        } catch (Exception e) {
            System.out.println("Checkbox not exist");


        }
    }
    @Test
    public void test02(){
        driver.findElement(By.id("btn")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[name()='svg']//*[name()='g'][12]")));
    }

    @AfterClass
    public void closeSession () {
        driver.quit();
        }
    }
