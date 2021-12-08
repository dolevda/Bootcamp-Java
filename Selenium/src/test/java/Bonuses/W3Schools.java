package Bonuses;

import com.opencsv.CSVWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class W3Schools {

    private WebDriver driver;
    private W3SchoolPage w3SchoolPage;
    private String[] temp;
    private List<String> companyEuropeCountries = new ArrayList<>();
    private List<String> expectedCountries = Arrays.asList("Germany", "Austria", "UK", "Italy");

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        w3SchoolPage = PageFactory.initElements(driver, W3SchoolPage.class);
    }

    @Test
    public void test01_numOfRows() {
        System.out.println("num of rows: " + w3SchoolPage.rows.size());
        Assert.assertEquals(w3SchoolPage.rows.size(), 7);
    }

    @Test
    public void test02_numOfCols() {
        System.out.println("num of cols: " + w3SchoolPage.cols.size());
        Assert.assertEquals(w3SchoolPage.cols.size(), 3);
    }

    @Test
    public void test03_printEuropeCountries() {
        addEuropeCountriesToList();
        for (String companyEurope : companyEuropeCountries)
            System.out.println(companyEurope);

    }

    @Step
    public void addEuropeCountriesToList() {
        for (int i = 2; i < w3SchoolPage.rows.size(); i++) {
            if (checkIfEurope(i)) {
                addToListEurope(i);
            }
        }

    }

    @Step
    public boolean checkIfEurope(int i) {
        if (expectedCountries.contains(driver.findElement(By.xpath("//table[@id='customers']/tbody/tr[" + i + "]/td[3]")).getText()))
            return true;
        return false;
    }

    @Step
    public void addToListEurope(int i) {
        companyEuropeCountries.add(driver.findElement(By.xpath("//table[@id='customers']/tbody/tr[" + i + "]/td[1]")).getText());
    }


    @Test
    public void test04_writeToCSV() throws IOException {
            // first create file object for file placed at location
            // specified by filepath
            File file = new File("C:\\Automation\\TestAutomation\\Selenium\\CsvFiles\\csvW3.txt");
            try {
                // create FileWriter object with file as parameter
                FileWriter outputfile = new FileWriter(file);
                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(outputfile);
                // adding header to csv
                String[] head = new String[w3SchoolPage.cols.size()];
                for (int i = 0; i < w3SchoolPage.cols.size(); i++) {
                    head[i] = w3SchoolPage.cols.get(i).getText();
                }
                writer.writeNext(head);
                // adding data to csv
                for (int i = 1; i < w3SchoolPage.rows.size(); i++) {
                    String[] data = new String[w3SchoolPage.cols.size()];
                    for (int j = 0; j < w3SchoolPage.cols.size(); j++) {
                        data[j] = w3SchoolPage.rows.get(i).findElements(By.tagName("td")).get(j).getText();
                    }
                    writer.writeNext(data);
                }
                // closing writer connection
                writer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }


}
