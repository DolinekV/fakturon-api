package com.dolinek.fakturon.Invoice.Web.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUpdateProductRequest
{
    @NotBlank(message = "field.required")
    private String name;

    private String description;

    private Double price;

    private Double priceTax;

    private Double taxAmount;
}
