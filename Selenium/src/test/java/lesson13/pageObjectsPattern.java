package lesson13;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class pageObjectsPattern {
    WebDriver driver;
    login logeInPage;
    PageObjectForm form;
    validationClickMe click;


    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://atidcollege.co.il/Xamples/webdriveradvance.html");
        logeInPage = PageFactory.initElements(driver, login.class);
        form = PageFactory.initElements(driver,PageObjectForm.class);
        click = PageFactory.initElements(driver,validationClickMe.class);
    }

    @Test
    public void test01(){
        logeInPage.loginAction("selenium", "webdriver");

    }

    @Test
    public void test02(){
        form.fullFormAction("QA","27","Netivot");


    }
    @Test
    public void test03(){
        assertTrue(click.btn_clickMe.isDisplayed());
    }


    @AfterClass
    public void closeSession(){
        driver.quit();
    }

}
