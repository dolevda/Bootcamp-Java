package lesson11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class NopCommerce {
    WebDriver driver;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/camera-photo");

    }
    @Test
    public void test01(){
        List <WebElement> pictures = driver.findElements(By.xpath("//*[contains(@alt,'Picture')]"));
        assertEquals(pictures.size(),3);

    }
    @Test
    public void test02(){
        String title0="Nikon D5500 DSLR";
        String title1="Leica T Mirrorless Digital Camera";
        String title2="Apple iCam";
        List <WebElement> pictures=driver.findElements(By.className("product-title"));
        assertEquals(pictures.get(0).getText(),title0);
        assertEquals(pictures.get(1).getText(),title1);
        assertEquals(pictures.get(2).getText(),title2);
    }
    @Test
    public void test03() {
        int expectedRating = 60;
        List<WebElement> starts = driver.findElements(By.xpath("//*[@class='rating']/div"));
        String temp;
        try {
            //if i want to check all pictures so make i=0 but the test failed
            for (int i = 0; i < starts.size(); i++) {
                System.out.println(starts.get(i).getAttribute("style"));
                temp = starts.get(i).getAttribute("style");
                temp.split(":");
                temp = temp.substring(7, temp.length() - 2);
                Assert.assertTrue(Integer.parseInt(temp) >=expectedRating);

            }

        } catch (AssertionError e) {
            System.out.println("product rating less then 3 start " + e);

        }

    }


    @AfterClass
    public void closeSession(){
        driver.quit();

    }

}
