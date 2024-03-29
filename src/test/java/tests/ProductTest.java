package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import request.RequestFactory;

import java.util.HashMap;
import java.util.Map;

public class ProductTest {

    RequestFactory requestFactory;

    @BeforeClass
    public void setUp(){
        requestFactory = new RequestFactory();
        requestFactory.createToken();
    }
    @Test
    public void healthCheck(){
        requestFactory.checkIfPageIsRunning().then().statusCode(201);
    }
    @Test
    public void goToBookingsPage(){
        requestFactory.getAllBookingsPage().then().log().all().statusCode(200);
    }
    @Test
    public void goToBooking(){
        requestFactory.getBookingWithIdPage("87").then().log().all().statusCode(200);
    }
    @Test
    public void addNewBooking(){
        String requestPayload = "{\n" +
                "    \"firstname\" : \"Pati\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestFactory.addBooking(requestPayload).then().log().all().statusCode(200);
        //check if the response payload equals to expected

    }
    @Test
    public void updateBooking(){
        String requestPayload = "{\n" +
                "    \"firstname\" : \"Pati\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        var response = requestFactory.addBooking(requestPayload).then().statusCode(200);
        var id = response.extract().path("bookingid").toString();
        String requestUpdatedPayload = "{\n" +
                "    \"firstname\" : \"Joe\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        requestFactory.updateBooking(id,requestUpdatedPayload).then().log().all().statusCode(200);
    }
    @Test
    public void partialUpdate(){
        String requestPayload = "{\n" +
                "    \"firstname\" : \"Pati\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        var response = requestFactory.addBooking(requestPayload).then().statusCode(200);
        var id = response.extract().path("bookingid").toString();

        String requestUpdate = "{ \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\"}";

        requestFactory.partialUpdateBooking(id,requestUpdate).then().log().all().statusCode(200);
    }
    @Test
    public void deleteBooking(){
        String requestPayload = "{\n" +
                "    \"firstname\" : \"Pati\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        var response = requestFactory.addBooking(requestPayload).then().log().all().statusCode(200);
        var id = response.extract().path("bookingid").toString();

        requestFactory.deleteBooking(id).then().statusCode(201);
        requestFactory.getBookingWithIdPage(id).then().statusCode(404);
    }


    }
