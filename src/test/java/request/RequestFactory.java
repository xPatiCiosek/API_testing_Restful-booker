package request;

import client.RestClient;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestFactory {


    private String access_token;

    public String getAccess_token(){
        return access_token;
    }
    public void setAccess_token(String newToken){
        this.access_token = newToken;
    }



     RestClient restClient;
     //add methods that might be helpful later
     //sort them in different classes based on page you are working with ex. HomePage, LoginPage

    public RequestFactory(){
        restClient = new RestClient();
    }
    public void createTokenPage(){
        String requestPayload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        //var access_token = restClient.SendPostRequest("auth", requestPayload).then().extract().path("token");

        var response = restClient.SendPostRequest("auth", requestPayload);
        String token = response.then().extract().path("token");
        setAccess_token(token);
        System.out.println("this is your token : " + access_token);

        //return restClient.SendPostRequest("auth", requestPayload).then().extract().path("token");


    }
    public Response getAllBookingsPage(){
        return restClient.SendGetRequest("/booking");
    }
     public Response getBookingWithIdPage(String id){
         //change to correct endpoint or create a var for it
         return restClient.SendGetRequest("/booking/"+id);
     }
    public Response addBooking(String requestPayload){
        return restClient.SendPostRequest("/booking",requestPayload);
    }
    public Response updateBooking(String id,String requestPayload){
        return restClient.SendPutRequest("/booking/"+id,requestPayload);
    }

}
