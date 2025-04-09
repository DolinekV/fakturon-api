package com.dolinek.fakturon.Invoice.Web.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddressDTO
{
    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String zip;

    @NotBlank
    private String city;

    @NotBlank
    private String state;
}
