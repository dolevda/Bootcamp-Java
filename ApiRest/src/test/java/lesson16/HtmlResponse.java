package lesson16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class HtmlResponse {
    private Document doc;
    private String Width = "250";
    private String Height = "200";
    private String category = "All Categories";

    @BeforeClass
    public void startSession() throws IOException {
        doc = Jsoup.connect("https://www.ebay.com/").get();
    }

    @Test
    public void test01() {
        assertEquals(doc.getElementById("gh-logo").attr("width"), Width);
    }

    @Test
    public void test02() {
        assertEquals(doc.getElementById("gh-logo").attr("height"), Height);
    }

    @Test
    public void test03() {
        assertEquals(doc.getElementsByAttributeValue("id", "gh-cat").get(0).text(), category);
    }



}
