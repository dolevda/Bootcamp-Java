package lesson10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;

import static org.testng.AssertJUnit.assertEquals;


public class ExternalFiles {
    WebDriver driver;
    @BeforeClass
     public void startSession(){
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.get(getData("URL"));
     }

     @Test
     public void test01(){
        driver.findElement(By.id("weight")).sendKeys(getData("WEIGHT"));
        driver.findElement(By.id("hight")).sendKeys(getData("HEIGHT"));
        driver.findElement(By.id("calculate_data")).click();
        assertEquals(driver.findElement(By.id("bmi_result")).getAttribute("value"),getData("EXPECTEDRESULTBMI"));

    }

     @AfterMethod
    public void closeSession(){
        driver.quit();
     }





    public String getData (String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("Files/externalFile.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        }
        catch(Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }


}
