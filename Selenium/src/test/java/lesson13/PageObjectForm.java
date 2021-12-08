package lesson13;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageObjectForm {
    @FindBy(how = How.ID ,using = "occupation")
    public WebElement txt_occupation;

    @FindBy(how = How.ID ,using =  "age")
    public WebElement txt_age;

    @FindBy(how = How.ID ,using = "location")
    public WebElement txt_location;

    @FindBy(how = How.XPATH,using = "//a/input")
    public WebElement btn_clickMe;



    public void fullFormAction(String occupation,String age, String location){
        txt_occupation.sendKeys(occupation);
        txt_age.sendKeys(age);
        txt_location.sendKeys(location);
        btn_clickMe.click();


    }

}
