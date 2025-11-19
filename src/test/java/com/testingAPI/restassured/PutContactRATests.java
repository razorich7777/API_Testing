package com.testingAPI.restassured;

import com.testingAPI.core.TestBase;
import com.testingAPI.dto.ContactDTO;
import com.testingAPI.dto.ErrorDTO;
import com.testingAPI.dto.UptdateContactDTO;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PutContactRATests extends TestBase {
    String id;

    @BeforeMethod
    public void precondition(){

        ContactDTO contact = ContactDTO.builder()
                .name("Sergey")
                .lastName("Perlov")
                .email("Test@gmail.com")
                .phone("1234567890")
                .address("Tel Aviv")
                .description("QA Python MID").build();

        String message = given().
                header(AUTHORIZATION, TOKEN)
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract().path("message");
        System.out.println(message);

        String[] split = message.split(": ");
        id = split[1];
    }
    @Test
    public void putContactSuccessChangeDescTest(){
        UptdateContactDTO contactChanged = UptdateContactDTO.builder()
                .id(id)
                .name("Sergey")
                .lastName("Perlov")
                .email("Test@gmail.com")
                .phone("1234567890")
                .address("Tel Aviv")
                .description("Programmer C#").build();

        given()
                .header(AUTHORIZATION, TOKEN)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(contactChanged)
                .when()
                .put("/contacts")
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat().body("message", equalTo("Contact was updated"));
              //  .extract().body().path("message")
       // System.out.println(message);
        //   assertThat().body("message", equalTo(""));
            //    .extract().body().as(ErrorDTO.class);
                //.extract().body().path("message");

    }
}
