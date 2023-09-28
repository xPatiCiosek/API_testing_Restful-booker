package tests;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import request.RequestFactory;


public class ProductTest {

    RequestFactory requestFactory;

    @BeforeClass
    public void setUp(){
        requestFactory = new RequestFactory();
        requestFactory.createToken();
    }
    @Test
    public void goToHomePage(){
        requestFactory.getAllBookingsPage().then().log().all().statusCode(200);
    }
    @Test
    public void goToUserPage(){
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

    }

    }
