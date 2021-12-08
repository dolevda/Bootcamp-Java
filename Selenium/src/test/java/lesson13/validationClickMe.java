package lesson13;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.annotations.Test;

public class validationClickMe {

    @FindBy(how = How.XPATH, using = "//a/button")
    public WebElement btn_clickMe;


}
