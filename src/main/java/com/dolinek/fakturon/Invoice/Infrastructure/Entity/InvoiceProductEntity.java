package com.dolinek.fakturon.Invoice.Infrastructure.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceProductEntity
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private InvoiceEntity invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private ProductEntity product;

    private String customName;
    private Double customPrice;
    private Double customPriceTax;
    private Double customTaxAmount;
}
