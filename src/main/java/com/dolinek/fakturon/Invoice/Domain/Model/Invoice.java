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
    private String id;
    private Customer customer;
    private List<Product> products;

    public static Invoice createNew(String id, Customer customer, List<Product> products)
    {
        return new Invoice(id, customer, products);
    }
}