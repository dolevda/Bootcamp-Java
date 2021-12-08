package lesson07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.bind.Element;
import java.util.ArrayList;
import java.util.List;

public class LocatorBasicB {
    private WebDriver driver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wikipedia.org/");

    }

    @Test
    public void fountElement(){

        List<WebElement> listElement = new ArrayList<>();

        WebElement logo = driver.findElement(By.className("central-featured-logo"));
        WebElement searchLanguage=driver.findElement(By.id("searchLanguage"));
        WebElement searchInput=driver.findElement(By.id("searchInput"));
        WebElement sidebarContent = driver.findElement(By.className("footer-sidebar-content"));
        listElement.add(logo);
        listElement.add(searchLanguage);
        listElement.add(searchInput);
        listElement.add(sidebarContent);

       for (int i=listElement.size()-1 ; i>=0 ; i--){
           System.out.println(listElement.get(i));

       }


    }


    @AfterClass
    public void closeSession(){
        driver.quit();
    }

}
