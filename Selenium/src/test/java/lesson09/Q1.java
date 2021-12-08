package lesson09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class Q1 {

    private WebDriver driver;
    private List <WebElement> trs;


    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.techlistic.com/p/demo-selenium-practice.html");
    }
    @Test
    public void test01(){
        String valueTotal = driver.findElement(By.xpath("//td[@colspan='7']")).getText();
        String [] numTotal = valueTotal.split(" ");
        int numStructure = Integer.parseInt(numTotal[0]);
        WebElement tbody = driver.findElement(By.xpath("//tbody[1]"));
        trs = tbody.findElements(By.tagName("tr"));
        assertEquals(trs.size(),numStructure);

    }
    @Test
    public void test02(){
        WebElement table = driver.findElement(By.tagName("tbody"));
        List <WebElement> listTH = table.findElements(By.tagName("tr"));
        for (WebElement tr: listTH){
            System.out.println(tr.getText());
        }

    }

    @Test
    public void test03(){
        String shouldBe = "829m";
        String numInHeightBurj = driver.findElement(By.xpath("//tbody/tr/td[3]")).getText();
        assertEquals(numInHeightBurj,shouldBe);
    }
    @Test
    public void test04(){
        List <WebElement> th = driver.findElements(By.xpath("//table/tfoot/tr/*"));
        assertEquals(th.size() ,2);


    }

    @AfterClass
    public void closeSession(){
        driver.quit();
    }
}
