package com.dolinek.fakturon.Invoice.Domain.Model;

import com.dolinek.fakturon.Common.Domain.Model.Address;
import lombok.Data;

import java.util.Optional;

@Data
public class Customer {
    private Long id;
    private String name;
    private String cin;
    private String tin;
    private String phone;
    private String email;
    private Address address;

    public Customer(Long id, String name, String cin, String tin, String email, String phone, Address address)
    {
        this.id = id;
        this.name = name;
        this.cin = cin;
        this.tin = tin;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Customer(String name, String cin, String tin, String email, String phone, Address address)
    {
        this.name = name;
        this.cin = cin;
        this.tin = tin;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public static Customer createNew(String name, String cin, String tin, String email, String phone, Address address)
    {
        return new Customer(name, cin, tin, email, phone, address);
    }
}
