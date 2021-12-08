package Bonuses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Base {
    protected WebDriver driver;
    protected TablePage tablePage;
    protected int sumRows;
    protected List<WebElement> temp;
    SoftAssert soft = new SoftAssert();
    protected float avrSalary;
}
