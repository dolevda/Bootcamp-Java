package lesson17;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class PostPutDelete {

    private String url ="http://localhost:9000/";
    public static RequestSpecification httpRequest;
    public static Response response;

    @BeforeClass
    public void startSession(){

        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json");

    }

    @Test
    public void test01(){
        JSONObject params = new JSONObject();

        params.put("firstName","Dolev");
        params.put("lastName","Sigron");
        params.put("email","dolevdadon1894@gmail.com");
        params.put("programme","QA");

        httpRequest.body(params.toJSONString());
        response = httpRequest.post("/student");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(),201);


    }
    @Test
    public void test02(){
        List<String> courses = new ArrayList<String>();
        courses.add("Java Course");
        courses.add("CSharp Course");
        courses.add("Python Course");

        JSONObject paramsCourse = new JSONObject();

        paramsCourse.put("firstName","Sali");
        paramsCourse.put("lastName","Sigron");
        paramsCourse.put("email","dolevSigron1894@gmail.com");
        paramsCourse.put("programme","xxxx");

        httpRequest.body(paramsCourse.toJSONString());
        response = httpRequest.post("/student");

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(),201);





    }

    @Test
    public void test03(){
        JSONObject paramsUpdate = new JSONObject();

        paramsUpdate.put("firstName","update");
        paramsUpdate.put("lastName","update");
        paramsUpdate.put("email","dolevdad111894@gmail.com");
        paramsUpdate.put("programme","update");

        httpRequest.body(paramsUpdate.toJSONString());
        response = httpRequest.put("/student/1");
        ;

        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(),200);

    }

    @Test
    public void test04(){
        response = httpRequest.delete("/student/1");
        System.out.println(response.getBody().asString());
        assertEquals(response.getStatusCode(),204);

    }

}
