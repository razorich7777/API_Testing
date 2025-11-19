package com.testingAPI.tests;

import com.testingAPI.core.TestBase;
import io.restassured.http.ContentType;
import org.apache.http.auth.AUTH;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddContactTests extends TestBase {
    String token;

//    @BeforeMethod
//    public void preRequest(){
//        String responseToken = given()
//                .contentType(ContentType.JSON)
//                .body()
//                .when()
//                .post("user/login/usernamepassword")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().path("token");
//    }
//
//    @Test
//    public void addContactStatus(){
//        String message = given()
//                .header(AUTH, token)
//                .contentType(ContentType.JSON)
//                .body()
//                .when()
//                .post("contacts")
//                .then()
//                .assertThat().statusCode(200)
//                .extract().path("message");
//        System.out.println(message);
//    }
}
