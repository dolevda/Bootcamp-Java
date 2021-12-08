package bunosReqres;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


public class ActionAPI extends CommonOps {

    @Test
    public void printUserBody() {
        response.prettyPrint();
    }

    @Test
    public void getReport() {
        System.out.println(response.time());
    }

    @Test
    public void getContentType() {
        System.out.println(response.getContentType());
    }

    @Test
    public int getStatusCodeTest() {
        System.out.println(response.getStatusCode());
        return response.getStatusCode();
    }

    @Test
    public void getHeaders() {
        System.out.println(response.getHeaders().toString());
    }

    @Test
    public void getHeaderText() {
        System.out.println("The Date is:" + response.getHeader("Date").toString());
    }

    @Test
    public void printUser4() {
        System.out.println(js.get("data[3].id").toString());
        System.out.println(js.get("data[3].email").toString());
        System.out.println(js.get("data[3].first_name").toString());
        System.out.println(js.get("data[3].last_name").toString());
        System.out.println(js.get("data[3].avatar").toString());

    }

    @Test
    public void printAllFirstName() {
        List<String> firstNames = js.getList("data.first_name");
        for (String name : firstNames) {

            System.out.println(name);
        }


    }
    @Test
    public void putData(){
        httpRequest.header("Content-Type" , "application/json");
        Params.put( "name","morpheus");
        Params.put("job","zion resident");
        response = httpRequest.put("api/users/2");
        response.prettyPrint();
        assertEquals(getStatusCodeTest(),200);

    }
    @Test
    public void postData(){
        Params.put("name","morpheus");
        Params.put("job", "leader");

        httpRequest.body(Params.toJSONString());
        response = httpRequest.post("/api/users");

        response.prettyPrint();
        assertEquals(getStatusCodeTest(),201);

    }

    @Test
    public void deleteData(){

        response = httpRequest.delete("api/users/2");
        response.prettyPrint();
        assertEquals(getStatusCodeTest(),204);

    }
    @AfterClass
    public void closeSession(){
        driver.quit();
    }
}