package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;


public class GetRequestQ2 {
    private String city = "Jerusalem,IL";
    private String myKey="8c2fc29f650ab651a5e1d332a46914ca";
    private String url="http://api.openweathermap.org/data/2.5/weather";

    private static RequestSpecification httpRequest;
    private Response response;
    private JsonPath js;
    private int humidity ;
    private String humidityWeb;
    private WebDriver driver;


    @BeforeClass
    public void start(){
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
    }

    @Test
    public void test01(){
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + myKey);
        js = response.jsonPath();
        assertEquals(js.get("sys.country"),"IL");
        humidity =js.get("main.humidity");

    }
    @Test
    public void test02(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://openweathermap.org/");
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*/form")));
            driver.findElement(By.xpath("//*/input")).sendKeys("Jerusalem,IL");
            driver.findElement(By.xpath("//*/input")).sendKeys(Keys.RETURN);
            driver.findElement(By.xpath("//*/tbody/tr[1]/td[2]/b[1]/a")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("current-container")));
            humidityWeb = driver.findElement(By.xpath("//*[@id='weather-widget']/div[2]/div[1]/div[1]/div[2]/ul/li[3]")).getText().split("\n")[1];
            assertEquals(humidity+ "%", humidityWeb);

        } catch (Exception e) {

            System.out.println("Test failed: " + e);
            fail("Test failed: " + e);
        }
        finally {
            driver.quit();

        }


    }

}
