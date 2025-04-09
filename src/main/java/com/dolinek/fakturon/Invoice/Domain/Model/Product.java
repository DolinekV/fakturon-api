package com.dolinek.fakturon.Invoice.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product
{
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double priceTax;
    private Double taxAmount;

    public static Product createNew(String name, String description, Double price, Double priceTax, Double taxAmount)
    {
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setPriceTax(priceTax);
        product.setTaxAmount(taxAmount);

        return product;
    }
}
