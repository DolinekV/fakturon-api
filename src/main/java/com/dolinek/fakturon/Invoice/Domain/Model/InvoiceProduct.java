package com.dolinek.fakturon.Invoice.Domain.Model;

import lombok.Getter;

@Getter
public class InvoiceProduct {
    private final Long id;
    private Long productId = null;
    private final String name;
    private final String description;
    private final Double price;
    private final Double priceWithTax;
    private final Double taxAmount;

    public InvoiceProduct(Long id, Product product, String name, String description, Double price, Double priceWithTax, Double taxAmount) {
        this.id = id;
        this.productId = product.getId();
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceWithTax = priceWithTax;
        this.taxAmount = taxAmount;
    }

    public InvoiceProduct(Long id, String name, String description, Double price, Double priceWithTax, Double taxAmount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priceWithTax = priceWithTax;
        this.taxAmount = taxAmount;
    }

    public static InvoiceProduct fromPredefinedProduct(Long id, Product product) {
        return new InvoiceProduct(id, product, product.getName(), product.getDescription(), product.getPrice(), product.getPriceTax(), product.getTaxAmount());
    }

    public static InvoiceProduct fromCustomProduct(Long id, String customName, String customDescription, Double price, Double priceWithTax, Double taxAmount) {
        return new InvoiceProduct(id, customName, customDescription, price, priceWithTax, taxAmount);
    }

    public boolean isPredefined() {
        return productId != null;
    }

    public boolean isCustom() {
        return productId == null;
    }
}
