package lesson11;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class superCalc {
        WebDriver driver;
        @BeforeClass
        public void startSession() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://juliemr.github.io/protractor-demo/");

        }

        @Test(description = "calculator ")
        @Description("Calculation of the multiplication table")
        public void test01() {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            for (int i = 10; i > 0; i--)
                for (int j = 10; j > 0; j--) {
                    driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys(Integer.toString(i));
                    driver.findElement(By.xpath("//select[@ng-model='operator']")).click();
                    driver.findElement(By.xpath("//option[@value='MULTIPLICATION']")).click();
                    driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys(Integer.toString(j));
                    driver.findElement(By.id("gobutton")).click();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    System.out.println(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText());

                }

        }
        @AfterClass
        public void closeSession() {
            driver.quit();
        }

}
