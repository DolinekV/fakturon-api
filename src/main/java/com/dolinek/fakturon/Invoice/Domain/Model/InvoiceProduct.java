package com.dolinek.fakturon.Invoice.Domain.Model;

import lombok.Getter;

@Getter
public class InvoiceProduct {
    private final Invoice invoice;
    private final Product product;
    private final String customName;
    private final Double price;
    private final Double priceWithTax;
    private final Double taxAmount;

    public InvoiceProduct(Invoice invoice, Product product, String customName, Double price, Double priceWithTax, Double taxAmount) {
        this.invoice = invoice;
        this.product = product;
        this.customName = customName;
        this.price = price;
        this.priceWithTax = priceWithTax;
        this.taxAmount = taxAmount;
    }

    public static InvoiceProduct fromPredefinedProduct(Invoice invoice, Product product) {
        return new InvoiceProduct(invoice, product, null, product.getPrice(), product.getPriceTax(), product.getTaxAmount());
    }

    public static InvoiceProduct fromCustomProduct(Invoice invoice, String customName, Double price, Double priceWithTax, Double taxAmount) {
        return new InvoiceProduct(invoice, null, customName, price, priceWithTax, taxAmount);
    }

    public boolean isPredefined() {
        return product != null;
    }

    public boolean isCustom() {
        return customName != null;
    }
}