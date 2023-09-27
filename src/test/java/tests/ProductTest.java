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
        requestFactory.createTokenPage();
    }
    @Test
    public void checkToken(){
        requestFactory.getAccess_token();
    }
    @Test
    public void goToHomePage(){
        requestFactory.getAllBookingsPage().then().log().all().statusCode(200);
    }
    @Test
    public void goToUserPage(){
        requestFactory.getBookingWithIdPage("87").then().log().all().statusCode(200);
    }
    @Test @Ignore
    public void addNewBooking(){
        String requestPayload = "";

        requestFactory.addBooking(requestPayload).then().log().all().statusCode(201);
    }

    }
