package lesson12;

import com.google.common.util.concurrent.Uninterruptibles;
import com.ibm.icu.impl.UResource;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.TestInstanceParameter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class ToDo {

    WebDriver driver;
    Actions action;
    int sumTodo;
    String edit;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/react/#/");
        action = new Actions(driver);
    }
    @Test(priority = 1)
    public void test01(){
        sumTodo=0;
        WebElement todos = todosLine();
        AddTodo(todos,"super market");
        AddTodo(todos,"Go to the mall");
        AddTodo(todos,"Fix my car");
        validSumTodo();



    }
    @Step
    public void AddTodo(WebElement lineTodo,String task){
        lineTodo.sendKeys(task);
        action.sendKeys(Keys.ENTER).build().perform();
        sumTodo++;

    }
    @Step
    public WebElement todosLine(){
        WebElement todo = driver.findElement(By.xpath("//input[@class='new-todo']"));
        action.moveToElement(todo).click();
        return todo;

    }
    @Step
    public void validSumTodo(){
        List<WebElement> todoList = driver.findElements(By.xpath("//ul[@class= 'todo-list']/li"));
        int sizeList = todoList.size();
        System.out.println(sizeList);
        try {
            assertEquals(sumTodo,sizeList);
        } catch (Exception e) {
            System.out.println("Failed!!");
        }

        System.out.println("Successfully!!");

    }

    @Test(dependsOnMethods ={"test01"},priority = 2)
    void test02(){
        WebElement deleteTodo =driver.findElement(By.xpath("//ul/li[1]/div/label"));
        action.click(deleteTodo);
        WebElement X=driver.findElement(By.xpath("//ul/li[1]/div/button"));
        action.click(X).build().perform();
        sumTodo--;
        validSumTodo();


    }

    @Test(dependsOnMethods ={"test01"},priority = 3)
    public void test03(){
        edit= "edit todo!";
        WebElement editTodo =driver.findElement(By.xpath("//ul/li[1]/div/label"));
        action.moveToElement(editTodo);;
        action.doubleClick(editTodo);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL);
        action.sendKeys(edit);
        action.sendKeys(Keys.ENTER).build().perform();
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);



    }

    @Test(dependsOnMethods ={"test01"},priority = 4)
    public void test04(){
        WebElement O=driver.findElement(By.xpath("//ul/li[1]/div/input"));
        action.click(O);
        action.build().perform();
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);


    }

    @Test(dependsOnMethods ={"test01","test04"},priority = 5)
    public void test05(){
        WebElement all=driver.findElement(By.xpath("//footer/ul/li[1]/a"));
        action.moveToElement(all).click().build().perform();
        WebElement active=driver.findElement(By.xpath("//footer/ul/li[2]/a"));
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        action.moveToElement(active).click().build().perform();
        WebElement completed=driver.findElement(By.xpath("//footer/ul/li[3]/a"));
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        action.moveToElement(completed).click().build().perform();
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);


    }

    @Test(dependsOnMethods ={"test01","test04"},priority = 6)
    public void test06(){
        WebElement clearComplete = driver.findElement(By.xpath("//footer/button"));
        action.moveToElement(clearComplete).click().build().perform();
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);

    }
    @AfterClass
    public void closeSession(){
        driver.quit();

    }
}
