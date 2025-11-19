package com.testingAPI.restassured;

import com.testingAPI.core.TestBase;
import com.testingAPI.dto.AllContactsDTO;
import com.testingAPI.dto.ContactDTO;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllContactTests  extends TestBase {

    @Test
    public void getAllContactsSuccess(){
        AllContactsDTO allContactsDTO = given().header(AUTHORIZATION, TOKEN)
                .when()
                .get("/contacts")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().body().as(AllContactsDTO.class);
        for(ContactDTO contact: allContactsDTO.getContacts()){
            System.out.println(contact.getId()+ " " + contact.getName());
        }
    }
}
