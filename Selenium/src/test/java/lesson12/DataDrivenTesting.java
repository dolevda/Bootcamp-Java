package lesson12;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DataDrivenTesting {
    @DataProvider(name = "data-provider-wiki")
    public Object[][] getDataObject() {
        return new Object[][]{
                {"Israel", "Israel"},
                {"Automation", "Automation"},
                {"BlahBlah", "Search results"}
        };

    }


    WebDriver drive;

    @BeforeClass
    public void startSession() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        drive = new ChromeDriver(opt);
        drive.manage().window().maximize();
        drive.get("https://en.wikipedia.org/wiki/Main_Page");


    }


    @Test(dataProvider = "data-provider-wiki")
    public void test01(String word, String result) {
        drive.findElement(By.name("search")).sendKeys(word);
        drive.findElement(By.id("searchButton")).click();
        assertEquals(drive.findElement(By.id("firstHeading")).getText(), result);
        drive.get("https://en.wikipedia.org/wiki/Main_Page");
    }


    @Test(dataProvider = "data-provider", dataProviderClass = ExternalProviderSCV.class)
    public void test02(String word, String result){
        drive.findElement(By.name("search")).sendKeys(word);
        drive.findElement(By.id("searchButton")).click();
        assertEquals(drive.findElement(By.id("firstHeading")).getText(), result);
        drive.get("https://en.wikipedia.org/wiki/Main_Page");



    }




    @AfterClass
    public void closeSession() {
        drive.quit();
    }


}




