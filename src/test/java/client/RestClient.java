package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class RestClient {
    //works as BasePage class?
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private String access_token;

    public String getAccess_token(){
        return access_token;
    }
    public void setAccess_token(String newToken){
        this.access_token = newToken;
    }

    public RestClient(){
        RestAssured.baseURI=BASE_URL;
    }

    public void SendTokenRequest(String uri, Object requestPayload){
        var response = given().contentType(ContentType.JSON).when().body(requestPayload).post(uri);
        String token = response.then().extract().path("token");
        setAccess_token(token);
    }

    public Response SendGetRequest(String uri){
        return given().when().get(uri);
    }
    ///html instead of json?
    public Response SendPostRequest(String uri, Object requestPayload){
        //.header("Authorization","Bearer" + token)
        return given().contentType(ContentType.JSON).when().body(requestPayload).post(uri);
    }
    public Response SendPutRequest(String uri, Object requestPayload){
        return given().header("Cookie", "token="+ access_token).contentType(ContentType.JSON).when().body(requestPayload).put(uri);
    }
    public Response SendPatchRequest(String uri, Object requestPayload){
        return given().header("Cookie", "token="+ access_token).contentType(ContentType.JSON).when().body(requestPayload).patch(uri);
    }
    public Response SendDeleteRequest(String uri){
        return given().when().delete(uri);
    }
}
