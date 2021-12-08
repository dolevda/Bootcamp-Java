package lesson11;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class MyActions {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_actions.html");
        action = new Actions(driver);

    }

    @Test
    public void test01() {
            WebElement draggable = driver.findElement(By.id("draggable"));
            WebElement droppable = driver.findElement(By.id("droppable"));
            action.dragAndDrop(draggable, droppable).build().perform();
            assertEquals(droppable.getText(), "Dropped!");
        }
    @Test
    public void test02(){
        List <WebElement> selectItems = driver.findElements(By.xpath("//*[@id='select_items']/li"));
        action.clickAndHold(selectItems.get(1)).clickAndHold(selectItems.get(2)).build().perform();
        }

    @Test
    public void test03(){
        action.doubleClick(driver.findElement(By.id("dbl_click"))).build().perform();
        assertEquals(driver.findElement(By.id("demo")).getText(),"Hello World");

    }
    @Test
    public void test04() {
        WebElement elem = driver.findElement(By.id("mouse_hover"));
        action.moveToElement(elem).build().perform();
        assertTrue(driver.findElement(By.cssSelector("span[style='background-color: rgb(255, 255, 0);']")).isDisplayed());

    }
    @Test
    public void test05(){
        WebElement element = driver.findElement(By.id("scrolled_element"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        assertEquals(driver.findElement(By.id("scrolled_element")).getText(), "This Element is Shown When Scrolled");
    }

    @AfterClass
    public void closeSession(){
        driver.quit();

    }

}
