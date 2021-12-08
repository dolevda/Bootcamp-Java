package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocatorAdvance {
    private WebDriver driver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_locators.html");
    }

    @Test
    public void test01(){
        WebElement locatorByID=driver.findElement(By.id("locator_id"));
        WebElement locatorByName=driver.findElement(By.name("locator_name"));
        WebElement locatorByTag=driver.findElement(By.tagName("p"));
        WebElement locatorByClassName=driver.findElement(By.className("locator_class"));
        WebElement locatorByLinkText=driver.findElement(By.linkText("myLocator(5)"));
        WebElement locatorByPartialLinkText=driver.findElement(By.partialLinkText("Find my locator "));
        WebElement locatorByCssSelector=driver.findElement(By.cssSelector("input[myname='selenium']"));
        WebElement locatorByXPath = driver.findElement(By.xpath("//*[@id='contact_info_left']/button"));



        System.out.println(locatorByID);
        System.out.println(locatorByName);
        System.out.println(locatorByClassName);
        System.out.println(locatorByTag);
        System.out.println(locatorByLinkText);
        System.out.println(locatorByPartialLinkText);
        System.out.println(locatorByCssSelector);
        System.out.println(locatorByXPath);


        System.out.println(locatorByID.getText());
        System.out.println(locatorByName.getText());
        System.out.println(locatorByClassName.getText());
        System.out.println(locatorByTag.getText());
        System.out.println(locatorByLinkText.getText());
        System.out.println(locatorByPartialLinkText.getText());
        System.out.println(locatorByCssSelector.getAttribute("value"));
        System.out.println(locatorByXPath.getText());





    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }
}
