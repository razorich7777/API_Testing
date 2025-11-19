package com.testingAPI.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AuthReqDTO {

    private String username;
    private String password;

}
