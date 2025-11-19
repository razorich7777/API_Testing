package com.testingAPI.core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVub0BnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc2MzkxMjYwNSwiaWF0IjoxNzYzMzEyNjA1fQ._8tkkRsTvlmE_vSFJQ3q7EpUtahm-BkGFFKc5zeSQYE";
    public static final String AUTHORIZATION = "Authorization";
    @BeforeMethod
    public void init(){
        RestAssured.baseURI  = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "/v1";

    }
}
