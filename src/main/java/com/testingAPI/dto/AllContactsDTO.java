package com.testingAPI.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class AllContactsDTO {

    private List<ContactDTO> contacts ;
}
