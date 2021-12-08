package lesson06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriverObjectMethode {

    private WebDriver driver;
    private final String ExpectedTitle="IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows";
    private final String ExpectedUrl="https://www.imdb.com/";

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.imdb.com/");

    }
    @Test
    public void test01(){
        driver.navigate().refresh();
        String title=driver.getTitle();
        System.out.println("The title is: "+title);
        if (title.equals(ExpectedTitle))
            System.out.println("Titles is the same");
        else
            System.out.println("Title isn't the same!!");
        String url=driver.getCurrentUrl();
        System.out.println("The url is: "+url);
        if (url.equals(ExpectedUrl))
            System.out.println("Urls is the same");
        else
            System.out.println("Urls isn't the same!!");

    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }



}
