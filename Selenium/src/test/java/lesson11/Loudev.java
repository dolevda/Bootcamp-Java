package lesson11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Loudev {

    WebDriver driver;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://loudev.com/");
    }

    @Test
    public void verifyElements() {
        MethodeVerifyElements.verifyElements(driver);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }



}