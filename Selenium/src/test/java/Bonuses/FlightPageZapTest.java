package Bonuses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class FlightPageZapTest {
    @FindBy(how = How.XPATH, using = "//*[@id='dateTo']")              // date box input
    private WebElement calander;
    @FindBy(how = How.XPATH, using = "//div[@class='col-md-2 form-group']/button[@class='form-control btn btn-default']") //search button
    protected WebElement searchBtn;
    @FindBy(how = How.XPATH, using = "//*[@id='from']")         //catch select menu "from"
    private WebElement from;
    @FindBy(how = How.XPATH, using = "//select[@id='to']")        //catch select menu "to"
    private WebElement to;
    @FindBy(how = How.XPATH, using = "//div[@class='col-md-12']/h4")                  //msg of result
    private WebElement resultMsg;
    @FindBy(how = How.XPATH, using = "//table[@id='no-more-tables']/tbody/tr/td[1]")        //found flight number
    private WebElement flightNum;
    @FindBy(how = How.XPATH, using = "//table/tbody/tr")        //found size of table (rows)
    private List<WebElement> howManyFlights;
    @FindBy(how = How.XPATH, using = "//table[@id='no-more-tables']/tbody/tr/td[5]/span/button") //reservation btn
    private WebElement bookFlightBtn;
    @FindBy(how = How.XPATH, using = "//alert/div")        //msg the flight is booked
    private WebElement bookedMsg;
    @FindBy(how = How.XPATH, using = "//div[@class='hidden-xs']/ul/li/a")        //reservation button
    private WebElement reserveBtn;
    public void pickDate(String date) {
        calander.clear();
        calander.sendKeys(date);
        searchBtn.click();
    }
    public void flightFrom(String cityName) {
        Select city= new Select(from);
        city.selectByValue(cityName);
    }
    public void flightTo(String cityName) {
        Select city= new Select(to);
        city.selectByValue(cityName);
    }
    public void verify() {
        String result = "";
        if (resultMsg.getText().contains("NO RESULTS")) {
            result = resultMsg.getText();
            Assert.assertEquals(result, "NO RESULTS ARE FOUND");
        } else if (resultMsg.getText().contains("SEARCH RESULTS")) {
            result = flightNum.getText();
            Assert.assertEquals(result, "UA1029");
            bookFlightBtn.click();
        }
    }
    public void isBooked() {
        Assert.assertEquals(bookedMsg.getText(), "The flight \"UA1029\"\" is booked.");
        reserveBtn.click();
    }
    public void verifyThereAreFlights()
    {
        Assert.assertEquals(howManyFlights.size(),2);
    }
}
