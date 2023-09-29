package request;

import client.RestClient;

import io.restassured.response.Response;


public class RequestFactory {


//    private String access_token;
//
//    public String getAccess_token(){
//        return access_token;
//    }
//    public void setAccess_token(String newToken){
//        this.access_token = newToken;
//    }



     RestClient restClient;
     //add methods that might be helpful later
     //sort them in different classes based on page you are working with ex. HomePage, LoginPage

    public RequestFactory(){
        restClient = new RestClient();
    }
    public void createToken(){
        String requestPayload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        restClient.SendTokenRequest("auth",requestPayload);

    }
    public Response getAllBookingsPage(){
        return restClient.SendGetRequest("/booking");
    }
     public Response getBookingWithIdPage(String id){
         //change to correct endpoint or create a var for it
         return restClient.SendGetRequest("/booking/"+id);
     }
    public Response addBooking(Object requestPayload){
        return restClient.SendPostRequest("/booking",requestPayload);
    }
    public Response updateBooking(String id,Object requestPayload){
        return restClient.SendPutRequest("/booking/"+id,requestPayload);
    }
    public Response partialUpdateBooking(String id,Object requestPayload){
        return restClient.SendPatchRequest("/booking/"+id,requestPayload);
    }

}
