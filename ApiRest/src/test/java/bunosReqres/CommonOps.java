package bunosReqres;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class CommonOps {
    protected static WebDriver driver;
    protected static String url;

    protected static RequestSpecification httpRequest;
    protected Response response;
    protected  JSONObject Params = new JSONObject();

    protected JsonPath js;

    @BeforeClass
    public void startSession(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        setUrl("https://reqres.in/");
        driver.get(url);
        initAPI();
        getDataUsers();
        initJS();

    }

    @Step("Initialize API")
    public void initAPI(){
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");
    }

    @Step("Initialize url")
    public void setUrl(String sendUrl){
        url = sendUrl;
    }

    @Step("Get Data from sever")
    private void getDataUsers(){
        response = httpRequest.get(url+"api/users");
    }
    @Step
    private void initJS(){
        js = response.jsonPath();
    }



}
