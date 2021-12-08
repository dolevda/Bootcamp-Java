package lesson08;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AssertAndVerifications {
    WebDriver driver;
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi/");

    }

    @Test
    public void test01(){
        driver.findElement(By.id("weight")).sendKeys("54");
        driver.findElement(By.id("hight")).sendKeys("167");
        driver.findElement(By.id("calculate_data")).click();


        String expectedResult = "19";
        String currentlyResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
        assertEquals(expectedResult,currentlyResult);
    }

    @Test
    public void test02(){
        System.out.println("Height size: " + driver.findElement(By.id("calculate_data")).getSize().getHeight());
        System.out.println("Width size: " + driver.findElement(By.id("calculate_data")).getSize().getWidth());
        System.out.println("Location X: " + driver.findElement(By.id("calculate_data")).getLocation().x);
        System.out.println("Location Y: " + driver.findElement(By.id("calculate_data")).getLocation().y);

        assertTrue(driver.findElement(By.id("calculate_data")).isEnabled());
        assertTrue(driver.findElement(By.id("calculate_data")).isDisplayed());
        assertFalse(driver.findElement(By.id("calculate_data")).isSelected());
        assertEquals(driver.findElement(By.id("calculate_data")).getTagName(),"input");
        assertEquals(driver.findElement(By.id("calculate_data")).getAttribute("value"),"Calculate BMI");
        assertFalse(driver.findElement(By.id("new_input")).isDisplayed(),"Checking Element (new_input) is Displayed");



    }



    @AfterClass
    public void closeSession(){
        driver.quit();

    }


}
