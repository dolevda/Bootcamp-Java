package Bonuses;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ZapTest {
    WebDriver driver;
    LoginPageZapTest login;
    FlightPageZapTest flight;
    ResevationPageZapTest reseve;

    @BeforeClass
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.zaptest.com/login/");
        login = PageFactory.initElements(driver, LoginPageZapTest.class);
        flight = PageFactory.initElements(driver, FlightPageZapTest.class);
        reseve = PageFactory.initElements(driver, ResevationPageZapTest.class);
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
    }
    @Test
    public void test01() {
        login.loginPage();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }

    @Test
    public void test02()    //two days before
    {
        flight.flightFrom("NYC");
        flight.flightTo("LAS");
        flight.pickDate("2021-12-3");
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.SECONDS);
        flight.verify();
    }
    @Test
    public void test03()    //two days ahead
    {
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.SECONDS);
        flight.flightFrom("NYC");
        flight.flightTo("LAS");
        flight.pickDate("2021-12-7");
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.SECONDS);
        flight.verify();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        flight.isBooked();
    }
    @Test
    public void test04()    //reservation page
    {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        reseve.checkFlight();
        reseve.cancelFlight();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        reseve.checkreservation();
        reseve.flightSystem();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }
    @Test
    public void test05()    //check flight from atlanta to ny
    {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        flight.flightFrom("ATL");
        flight.flightTo("NYC");
        flight.searchBtn.click();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        flight.verifyThereAreFlights();
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }
}
