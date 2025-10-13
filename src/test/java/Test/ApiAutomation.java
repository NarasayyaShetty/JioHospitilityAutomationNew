package Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class ApiAutomation {
    RequestSpecification httpRequest;
    String email;
    String password;
    JSONObject obj;
    String token;
    String loginId;

    @BeforeMethod(alwaysRun=true)
    public void setup(){
        RestAssured.baseURI="https://qtrip-backend.labs.crio.do";
        RestAssured.basePath="/api/v1";
        httpRequest=RestAssured.given().log().all();
    }

    @Test(description="Registring the new user", priority=1)
    public void makeNewUser(){

        email=String.format("Test_%s.@gmail.com",UUID.randomUUID().toString());
        password="Test@123";
        obj=new JSONObject();
        obj.put("email",email);
        obj.put("password",password);
        obj.put("confirmpassword",password);


        Response response1=httpRequest.header("Content-Type","Application/json").body(obj.toString()).when().post("/register");
        System.out.println(response1.getStatusLine());
        int status=response1.getStatusCode();
        Assert.assertEquals(status,201,"Registration got failed");
    }

    @Test(description="Login to the application",priority=2)
    public void LoginToApp(){
        obj.remove("confirmpassword");
        Response response=httpRequest.header("Content-Type","application/json").body(obj.toString()).when().post("/login");
        String responseBody=response.body().asPrettyString();
        System.out.println(responseBody);
        System.out.println(response.getStatusLine());
        JsonPath js=new JsonPath(responseBody);
        token=js.getString("data.token");
        loginId=js.getString("data.id");
        System.out.println("Token is "+token+"   "+"loginId   :"+loginId);
    }

    @Test(description="Getting all the city details",priority=3)
    public void getAllCities(){
        Response response=httpRequest.when().get("/cities");
        System.out.println(response.getStatusLine());
        System.out.println(response.body().asPrettyString());
    }

}
