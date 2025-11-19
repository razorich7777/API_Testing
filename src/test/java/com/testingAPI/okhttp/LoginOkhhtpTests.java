package com.testingAPI.okhttp;

import com.google.gson.Gson;
import com.testingAPI.dto.AuthReqDTO;
import com.testingAPI.dto.AuthRespDTO;
import com.testingAPI.dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginOkhhtpTests {
    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();
    public static final MediaType JSON = MediaType
            .get("application/json;charset=utf-8");

    @Test
    public void loginSuccess() throws IOException {
        AuthReqDTO auth = AuthReqDTO.builder()
                .username("leno@gmail.com")
                .password("Bernd1234$")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(auth), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 200);

        AuthRespDTO responseDTO = gson.fromJson(response.body().string(), AuthRespDTO.class);

        String token = responseDTO.getToken();
        System.out.println(token);
        //eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVub0BnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTc2MzkxMjYwNSwiaWF0IjoxNzYzMzEyNjA1fQ._8tkkRsTvlmE_vSFJQ3q7EpUtahm-BkGFFKc5zeSQYE
    }
    @Test
    public void loginWithWrongEmail() throws IOException {
        AuthReqDTO auth = AuthReqDTO.builder()
                .username("lenogmail.com")
                .password("Bernd1234$")
                .build();
        RequestBody body = RequestBody.create(gson.toJson(auth), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(), 401);

        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        Assert.assertEquals(errorDTO.getMessage(), "Login or Password incorrect");
    }
}
