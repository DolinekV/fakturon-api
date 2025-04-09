package com.dolinek.fakturon.Common.Infrastructure.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity
{
    public String street;

    public String number;

    public String city;

    public String zip;

    public String state;
}
