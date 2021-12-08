package lesson16;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GetRequest {
    private String city = "Jerusalem,IL";
    private String myKey="8c2fc29f650ab651a5e1d332a46914ca";
    private String url="http://api.openweathermap.org/data/2.5/weather";

    private static RequestSpecification httpRequest;
    private Response response;


    @BeforeClass
    public void start(){
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
    }

    @Test
    public void test01(){
        response = httpRequest.get("?units=metric&q=" + city + "&appid=" + myKey);
        response.prettyPrint();
        System.out.println(response.getStatusCode());
        System.out.println(response.header("Date"));
        assertTrue(response.getContentType().contains("json"));
        assertEquals(response.getStatusCode(),200);

    }

    @Test
    public void test02(){



    }
}
