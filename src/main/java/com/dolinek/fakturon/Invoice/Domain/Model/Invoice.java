package com.dolinek.fakturon.Invoice.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice
{
    private Long id;
    private Customer customer;
    private List<InvoiceProduct> products;

    public static Invoice createNew(Long id, Customer customer, List<InvoiceProduct> products)
    {
        return new Invoice(id, customer, products);
    }
}
