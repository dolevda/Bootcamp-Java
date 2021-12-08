package lesson11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MethodeVerifyElements {

    public static void verifyElements(WebDriver checkDriver) {
        List<WebElement> elements= checkDriver.findElements(By.xpath("//div[@id='ms-aloha']/div[@class='ms-selectable']/ul[@class='ms-list']/li[@class='ms-elem-selectable']/span"));
        for (WebElement elem: elements)
            System.out.println(elem.getText());
    }

}

