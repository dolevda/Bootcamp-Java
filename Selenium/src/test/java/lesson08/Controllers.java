package lesson08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Controllers {
    private WebDriver driver;
    private final String firstName = "Dolev";
    private final String latName = "Sigron";

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_controllers.html");

    }
    @Test
    public void test01(){
        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(latName);
        Select continents = new Select(driver.findElement(By.id("continents")));
        continents.selectByVisibleText("Asia");
        driver.findElement(By.id("submit")).click();
        String usl = driver.getCurrentUrl();
        if(usl.contains(firstName)&& usl.contains(latName))
            System.out.println("Test Passed");
        else
            System.out.println("Test failed !!");

    }

    @Test
    public void test02(){
        driver.findElement(By.id("sex-1")).click();
        driver.findElement(By.id("exp-0")).click();
        driver.findElement(By.id("datepicker")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[1]/td[7]/a")).click();
        driver.findElement(By.id("profession-1")).click();
        driver.findElement(By.id("tool-2")).click();
        Select continents = new Select(driver.findElement(By.id("continents")));
        continents.selectByVisibleText("Asia");
        Select SeleniumCommands = new Select(driver.findElement(By.id("selenium_commands")));
        SeleniumCommands.selectByVisibleText("Browser Commands");

        driver.findElement(By.id("photo")).sendKeys("C:\\Users\\dolev\\Desktop\\gallery_28.6.21-org-eap-40.jpg"); // upload file
        driver.findElement(By.id("submit")).click();
        String[] url = driver.getCurrentUrl().split("%");
        String day = url[0].substring(url[0].length()-2);
        String month =url[1].substring(url[1].length()-2);
        String year = url[2].substring(2,6);
        System.out.println(year + "-" + month + "-" + day);


    }

    @AfterClass
    public void closeSession(){
        driver.quit();

    }



}
