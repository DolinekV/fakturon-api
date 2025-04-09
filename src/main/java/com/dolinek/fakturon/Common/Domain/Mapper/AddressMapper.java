package com.dolinek.fakturon.Common.Domain.Mapper;

import com.dolinek.fakturon.Common.Domain.Model.Address;
import com.dolinek.fakturon.Common.Infrastructure.Entity.AddressEntity;
import com.dolinek.fakturon.Invoice.Web.Dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper
{
    public AddressEntity toEntity(AddressDTO address)
    {
        AddressEntity addressEntity = new AddressEntity();

        return addressEntity;
    }

    public Address toAddress(AddressEntity addressEntity)
    {
        Address address = new Address(
                addressEntity.street,
                addressEntity.number,
                addressEntity.city,
                addressEntity.zip,
                addressEntity.state
        );

        return address;
    }

}
