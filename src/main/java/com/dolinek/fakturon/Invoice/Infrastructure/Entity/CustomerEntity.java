package com.dolinek.fakturon.Invoice.Infrastructure.Entity;

import com.dolinek.fakturon.Common.Infrastructure.Entity.AddressEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue
    public Long id;

    public String name;

    /* Company identification number */
    public String cin;

    /* Tax identification number */
    public String tin;

    @Column(unique = true)
    public String email;

    public String phone;

    @Embedded
    public AddressEntity billingAddress;

    @OneToMany(mappedBy = "customer")
    public List<InvoiceEntity> invoices;
}
