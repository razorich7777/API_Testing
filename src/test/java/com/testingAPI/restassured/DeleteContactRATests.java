package com.testingAPI.restassured;

import com.testingAPI.core.TestBase;
import com.testingAPI.dto.ContactDTO;
import com.testingAPI.dto.ErrorDTO;
import io.restassured.http.ContentType;
import lombok.Builder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactRATests extends TestBase {
    String id;

    @BeforeMethod
    public void precondition(){

        ContactDTO contact = ContactDTO.builder()
                .name("Sergey")
                .lastName("Tea")
                .email("Test@gmail.com")
                .phone("1234567890")
                .address("TelAviv")
                .description("QA").build();

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
    @Test(enabled = false)
    public void deleteContactSuccessTest(){
        given()
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete("contacts/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"));
              //  .extract().body().path("message");
      //  System.out.println(message);
    }
    @Test
    public void DeleteContactByWrongId(){
         given()
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete("/contacts/" + "9cd731fa-5d22-4333-933b-90fdc26a86db")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", containsString("not found in your contacts!"));
             //   .extract().body().as(ErrorDTO.class);

      //  System.out.println(errorDTO.getMessage());
    }
}
