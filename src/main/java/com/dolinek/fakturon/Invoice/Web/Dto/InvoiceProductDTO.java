package com.dolinek.fakturon.Invoice.Web.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceProductDTO {
    private Long productId;
    private String customName;
    private Double price;
    private Double priceTax;
    private Double taxAmount;
}
