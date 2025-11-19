package com.testingAPI.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UptdateContactDTO {
    private String id;
    private String name;
    private String email;
    private String lastName;
    private String phone;
    private String address;
    private String description;
}
