package bunosReqres;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class BookShop {

    WebDriver driver;
    Document doc;
    float seleniumSum=0;
    float sumJson=0;
    String url = "http://localhost:8080";
    public static RequestSpecification httpRequest;
    public static Response response;
    JsonPath js;
    float totalSumOfAllPageAPI=0;
    String jsonURL="http://localhost:8080/admin/orders.json?order=id_desc";


    @BeforeClass
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

    }

    @Test
    public void sumOfBooks() throws IOException {
        doc = Jsoup.connect("http://localhost:8080/").get();
        List<Element> values = doc.getElementsByClass("product");
        assertEquals(33, values.size());

    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    @Test
    public void calcOrderWeb() {
        driver.findElement(By.xpath("//*[@id='auth']/a[1]")).click();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='orders']/a")).click();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        List<WebElement> listOfTotal = driver.findElements(By.xpath("//td[@class='col col-total']"));
        String temp ;
        for (WebElement total:listOfTotal){
            temp = total.getText();
            temp= temp.split("\\$")[1];
            seleniumSum+=Float.parseFloat(temp);
        }

        System.out.println(seleniumSum);

    }
    @Test
    public void calcOrderJson(){
        response= httpRequest.get(jsonURL);
        js = response.jsonPath();
        List <String> listSumJson = js.getList("total_price");
        for(String totalJ:listSumJson){
            sumJson+=Float.parseFloat(totalJ);
        }
        System.out.println(sumJson);



    }

    @Test(dependsOnMethods = { "calcOrderWeb", "calcOrderJson" })
    public void compere(){
        assertEquals(seleniumSum,sumJson);
    }

    @Test
    public void tatalAll()
    {
        for (int i=1;i<11;i++)
        {
            jsonURL= "http://localhost:8080/admin/orders.json?order=id_desc&page="+i;
            calcOrderJson();
            totalSumOfAllPageAPI +=sumJson;
        }
        System.out.println("total sum of all pages is: "+totalSumOfAllPageAPI);
    }

}