package lesson09;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;


public class Synchronization {
    WebDriver driver;
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_synchronization.html");
    }

    @Test
    public void test01(){
        driver.findElement(By.id("rendered")).click();
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish2")));
        String output = driver.findElement(By.id("finish2")).getText();
        String expect = "My Rendered Element After Fact!";
        assertEquals(expect,output);

    }


    @Test
    public void test02(){
        driver.findElement(By.id("hidden")).click();
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
        driver.findElement(By.id("loading1"));

}

    @Test
    public void test03(){
        driver.findElement(By.id("btn")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.id("message")).isDisplayed());

    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }
}
