package Bonuses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class ResevationPageZapTest {

    @FindBy(how = How.XPATH, using = "//table[@class='table']/tbody/tr/td[1]")
    //check the flight is shown in table
    private WebElement reserveFlight;
    @FindBy(how = How.XPATH, using = "//table[@class='table']/tbody/tr/td[7]/button")        //cancel btn
    private WebElement cancelBtn;
    @FindBy(how = How.XPATH, using = "//*[@id='page-top']/app/header/div/div/div/div/ng-component/div/div/div/h3")
    //no reservation h3
    private WebElement NoReserve;
    @FindBy(how = How.XPATH, using = "//*[@id='page-top']/app/nav/div/div[1]/a")        //search flight button
    private WebElement searchFlightsBtn;

    void checkFlight() {
        Assert.assertEquals(reserveFlight.getText().equals("UA1029"), true);
    }

    public void cancelFlight() {
        cancelBtn.click();
    }

    public void checkreservation() {
        Assert.assertEquals(NoReserve.getText(), "NO RESERVATIONS");
    }

    public void flightSystem() {
        searchFlightsBtn.click();
    }
}
