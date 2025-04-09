package com.dolinek.fakturon.Invoice.Web.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCustomerRequest
{
    @NotBlank(message = "field.required")
    @Size(max = 100)
    private String name;

    private String cin;

    private String tin;

    @Email(message = "Not a valid email address.")
    @NotBlank(message = "field.required")
    private String email;

    private String phone;

    @Valid
    private AddressDTO address;
}
