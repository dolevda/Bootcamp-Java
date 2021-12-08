package lesson11;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson10.AutomationListeners;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(AutomationListeners.class)
public class ReportingSystem {
    WebDriver driver;
    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/bmi/");

    }
    @Test(priority = 0,description = "Enter value and validation")
    @Description("Enter value and test validation")
    public void test01(){
        enterValue("weight","hight","calculate_data");
        validation("19");
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

    @Step
    public void enterValue(String weight,String hight,String calculate_data){
        driver.findElement(By.id(weight)).sendKeys("54");
        driver.findElement(By.id(hight)).sendKeys("167");
        driver.findElement(By.id(calculate_data)).click();

    }
    @Step
    public void validation(String expectedResult){
        try {
            String currentlyResult = driver.findElement(By.id("bmi_result")).getAttribute("value");
            assertEquals(expectedResult,currentlyResult);
        }catch (AssertionError e){
            saveScreenshot(driver);
            fail("Assert failed see details"+e);
        }


    }
    @Attachment(value="page screenshot",type = "image.png")
    public byte[] saveScreenshot(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}
