package com.dolinek.fakturon.Invoice.Web.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateUpdateInvoiceRequest {
    private Long id;

    @NotBlank
    private Long customerId;

    private List<Long> productIds = new ArrayList<>();
}
