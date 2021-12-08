package Bonuses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class W3SchoolPage {

    @FindBy(xpath = "//table[@id='customers']/tbody")
    public WebElement table;
    @FindBy(xpath = "//table[@id='customers']/tbody/tr")
    public List<WebElement> rows;
    @FindBy(xpath = "//table[@id='customers']/tbody/tr/th")
    public List<WebElement> cols;
}
