package lesson09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SwitchAndNavigation {
    WebDriver driver;
    String output;


    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/ex_switch_navigation.html");

    }

    @Test
    public void test01(){
        driver.findElement(By.id("btnAlert")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text:"+ alert.getText());
        alert.accept();
        output = "Alert is gone.";
        assertEquals(driver.findElement(By.id("output")).getText(),output);
    }
    @Test
    public void test02(){
        driver.findElement(By.id("btnPrompt")).click();
        Alert popup = driver.switchTo().alert();
        popup.sendKeys("Dolev");
        System.out.println("Prompt text is: "+ popup.getText());
        popup.accept();
        output = "Dolev";
        assertEquals(driver.findElement(By.id("output")).getText(),output);

    }
    @Test
    public void test03(){
        driver.findElement(By.id("btnConfirm")).click();
        Alert alertConfirm = driver.switchTo().alert();
        System.out.println("Confirm Box text is:" + alertConfirm.getText());
        alertConfirm.accept();
        output= "Confirmed.";
        assertEquals(driver.findElement(By.id("output")).getText(),output);

    }
    @Test
    public void test04(){
        WebElement ifrm = driver.findElement(By.xpath("//*[@id='contact_info_left']/iframe"));
        driver.switchTo().frame(ifrm);
        System.out.println("IFrame text id:"+ driver.findElement(By.id("iframe_container")).getText());
        output="This is an IFrame !";
        assertEquals(driver.findElement(By.id("iframe_container")).getText(),output);
        driver.switchTo().defaultContent();



    }
    @Test
    public void test05(){
        String defaultTab =driver.getWindowHandle();
        driver.findElement(By.id("btnNewTab")).click();
        for (String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        System.out.println("Text in tab is: "+ driver.findElement(By.id("new_tab_container")).getText());
        output = "This is a new tab";
        assertEquals(driver.findElement(By.id("new_tab_container")).getText(),output);
        driver.close();
        driver.switchTo().window(defaultTab);


}
    @Test
    public void test06(){
        String firstWin = driver.getWindowHandle();
        driver.findElement(By.xpath("//*[@id='contact_info_left']/a")).click();
        for (String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        System.out.println("Text in open window is: "+ driver.findElement(By.id("new_window_container")).getText());
        output = "This is a new window";
        assertEquals(driver.findElement(By.id("new_window_container")).getText(),output);
        driver.close();
        driver.switchTo().window(firstWin);

    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }

}


