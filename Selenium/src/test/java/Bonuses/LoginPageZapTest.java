package Bonuses;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class LoginPageZapTest {
    @FindBy(how = How.XPATH, using= "//input[@name='username']")
    private WebElement userNameInput;
    @FindBy(how = How.XPATH, using= "//input[@name='password']")
    private WebElement passInput;
    @FindBy(how = How.XPATH, using= "//*[@class='btn btn-default']")
    private WebElement loginBtn;

    public void loginPage()
    {
        Uninterruptibles.sleepUninterruptibly(15, TimeUnit.SECONDS);
        userNameInput.click();
        userNameInput.sendKeys("test");
        passInput.click();
        passInput.sendKeys("demo");
        loginBtn.click();
    }

}
