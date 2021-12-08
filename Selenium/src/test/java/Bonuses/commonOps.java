package Bonuses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class commonOps extends  Base{
    //השתיים האחרים צריכים לרשת מזה
    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://datatables.net/");
        tablePage = PageFactory.initElements(driver, TablePage.class);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();

    }






}
