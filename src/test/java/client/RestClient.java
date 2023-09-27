package client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class RestClient {
    //works as BasePage class?
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";

    public RestClient(){
        RestAssured.baseURI=BASE_URL;
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
        return given().contentType(ContentType.JSON).when().body(requestPayload).put(uri);
    }
    public Response SendPatchRequest(String uri, Object requestPayload){
        return given().contentType(ContentType.JSON).when().body(requestPayload).patch(uri);
    }
    public Response SendDeleteRequest(String uri){
        return given().when().delete(uri);
    }
}
