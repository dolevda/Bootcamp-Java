package lesson12;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson11.MethodeVerifyElements;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class middleTest {
    WebDriver driver;
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/pizza/");
    }

    @Test
    public void test01(){
        try {
            Assert.assertEquals(driver.findElement(By.xpath("//span[@class='ginput_total ginput_total_5']")).getText(),"$7.50");

        }catch (AssertionError e) {
            System.out.println("The first price isn't 7.50$!");
        }

    }

    @Test
    public void test02(){

        driver.findElement(By.xpath("//input[@id='input_5_22_3']")).sendKeys("Dolev");
        driver.findElement(By.xpath("//input[@id='input_5_22_6']")).sendKeys("Sigron");
        driver.findElement(By.xpath("//select[@id='input_5_21']")).click();
        driver.findElement(By.xpath("//option[@value = 'Pick Up|0']")).click();
        try {
            Assert.assertEquals(driver.findElement(By.xpath("//span[@class='ginput_total ginput_total_5']")).getText(),"$10.50");

        }catch (AssertionError e) {
            System.out.println("The first price isn't $10.50!");
        }
    }

    @Test
    public void test03(){

        driver.findElement(By.xpath("//textarea[@id = 'input_5_20']")).sendKeys("088-234");
        driver.findElement(By.xpath("//input[@id='gform_submit_button_5']")).click();
        Alert popup = driver.switchTo().alert();
        String alertText = popup.getText();
        try {

            Assert.assertEquals(alertText,"Dolev Sigron 088-234");
            popup.accept();


        }catch (AssertionError e) {
            System.out.println("The name or coupon is wrong");
        }
    }

    @AfterClass
    public void closeSession() {

        driver.quit();
    }
}
