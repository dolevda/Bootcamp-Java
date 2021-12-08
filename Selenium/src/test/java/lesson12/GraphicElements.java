package lesson12;


import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GraphicElements {

    WebDriver driver;
    Screen screen;
    final String path= "C:\\Automation\\TestAutomation\\pictures\\";



    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://maps.google.com");
        screen = new Screen();
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

    }
    @Test
    public void googleMaps() throws FindFailed {

        screen.wait(path + "plus.png", 5);
        screen.click(path + "plus.png");
        screen.click(path + "plus.png");
        screen.click(path + "plus.png");
        screen.type(path + "search.png", "Tel-Aviv, israel");

     ;






    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }
}