package com.dolinek.fakturon.Common.Domain.Model;

import com.dolinek.fakturon.Common.Infrastructure.Entity.AddressEntity;
import com.dolinek.fakturon.Invoice.Web.Dto.AddressDTO;
import lombok.Data;

@Data
public class Address
{
    private String street;
    private String number;
    private String city;
    private String zip;
    private String state;

    public Address(String street, String number, String city, String zip, String state)
    {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }

    public static Address from(AddressDTO dto) {
        if (dto == null)
        {
            return null;
        }

        return new Address(dto.getStreet(),dto.getNumber(), dto.getCity(), dto.getZip(), dto.getState());
    }

    public static Address from(AddressEntity entity) {
        if (entity == null)
        {
            return null;
        }

        return new Address(entity.street, entity.number, entity.city, entity.zip, entity.state);
    }
}
