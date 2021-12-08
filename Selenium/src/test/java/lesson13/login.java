package lesson13;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class login {
    @FindBy(how = How.NAME ,using = "username2")
    public WebElement txt_userName;

    @FindBy(how = How.NAME,using = "password2")
    public WebElement txt_password;

    @FindBy(how = How.ID, using = "submit")
    public WebElement btn_submit;



    public void loginAction(String user,String password){
        txt_userName.sendKeys(user);
        txt_password.sendKeys(password);
        btn_submit.click();


    }



}
