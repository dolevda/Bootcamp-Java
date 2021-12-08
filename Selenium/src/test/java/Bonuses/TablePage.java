package Bonuses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class TablePage  {
    @FindBy(how = How.ID,using = "example_next")
    public WebElement btn_next;

    @FindBy(how = How.XPATH , using = "//*[@id='example']/tbody/tr")
    public List<WebElement> rowsFirstPage;

    @FindBy(how = How.XPATH , using = "//*[@id='example']/thead/tr/th")
    public List<WebElement> columnTable;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[1]")
    public List<WebElement> names;

    @FindBy(how = How.XPATH,using = "//table[@id='example']/tbody/tr/td[2]")
    public  List<WebElement> positions;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[4]")
    public  List<WebElement> ages;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[3]")
    public List<WebElement> offices;

    @FindBy(how = How.XPATH, using = "//*[@id='example']/tbody/tr/td[6]")
    public List<WebElement> salary;

}
