package lesson07;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsBasic {
    private WebDriver driver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");

    }

    @Test
    public void test01() {
        System.out.println(driver.findElement(By.id("selenium_logo")));
        System.out.println(driver.findElement(By.className("navbar-logo")));
        System.out.println(driver.findElement(By.className("navbar-brand")));
        System.out.println(driver.findElement(By.tagName("svg")));
        System.out.println(driver.findElements(By.tagName("svg")).get(0));

    }
    @Test
     public void test02(){
        List <WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links is: "+links.size());

        }

     @Test
    public void test03(){
        List <WebElement> linkSelenium = driver.findElements(By.partialLinkText("Selenium"));
         System.out.println("Number of links contain selenium is: "+linkSelenium .size());
     }

     @AfterClass
     public void closeSession(){
        driver.quit();

     }



}



