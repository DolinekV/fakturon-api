package com.dolinek.fakturon.Invoice.Web.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateUpdateInvoiceRequest {
    private Long id;

    @NotNull
    private Long customerId;

    private List<Long> productIds = new ArrayList<>();
}
