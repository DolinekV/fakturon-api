package com.dolinek.fakturon.Invoice.Infrastructure.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Double priceTax;

    private Double taxAmount;

    @PrePersist
    void calculatePriceTax()
    {
        this.priceTax = this.price * (100 * this.taxAmount);
    }
}
