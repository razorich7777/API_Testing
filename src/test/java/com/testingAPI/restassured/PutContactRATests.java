package com.testingAPI.restassured;

import com.testingAPI.core.TestBase;
import com.testingAPI.dto.AllContactsDTO;
import com.testingAPI.dto.ContactDTO;
import com.testingAPI.dto.ErrorDTO;
import com.testingAPI.dto.UptdateContactDTO;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PutContactRATests extends TestBase {
    static String id;
    static String first_desc;
    static String second_desc;

    @BeforeMethod
    public void precondition(){

        ContactDTO contact = ContactDTO.builder()
                .name("Sergey")
                .lastName("Perlov")
                .email("Test@gmail.com")
                .phone("1234567890")
                .address("Tel Aviv")
                .description("QA Python MID").build();
        first_desc = contact.getDescription();
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
        second_desc = contactChanged.getDescription();
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
    @AfterMethod
    public static void checkTheChanges(){
        AllContactsDTO allContactsDTO = given().header(AUTHORIZATION, TOKEN)
                .when()
                .get("/contacts")
                .then()
                .extract().body().as(AllContactsDTO.class);

        for(ContactDTO contact: allContactsDTO.getContacts()){
            if (contact.getId().equals(id)){
            System.out.println("In contactId " + contact.getId()
                    + " Was changed description from " + first_desc +" to " + contact.getDescription());
                Assert.assertEquals(contact.getDescription(), second_desc);
            }
        }
    }
}
