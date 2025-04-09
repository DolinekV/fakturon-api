package com.dolinek.fakturon.Invoice.Infrastructure.Mapper;

import com.dolinek.fakturon.Common.Domain.Model.Address;
import com.dolinek.fakturon.Common.Infrastructure.Entity.AddressEntity;
import com.dolinek.fakturon.Invoice.Domain.Model.Customer;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerEntity entity = new CustomerEntity();
        AddressEntity addressEntity = new AddressEntity();

        entity.setName(customer.getName());
        entity.setCin(customer.getCin());
        entity.setTin(customer.getTin());
        entity.setEmail(customer.getEmail());

        if (customer.getAddress() != null) {
            addressEntity.setStreet(customer.getAddress().getStreet());
            addressEntity.setNumber(customer.getAddress().getNumber());
            addressEntity.setCity(customer.getAddress().getCity());
            addressEntity.setState(customer.getAddress().getState());
        }

        entity.setBillingAddress(customer.getAddress() != null ? addressEntity : null);

        return entity;
    }

    public Customer toCustomer(CustomerEntity customerEntity) {
        return new Customer(
                customerEntity.name,
                customerEntity.cin,
                customerEntity.tin,
                customerEntity.email,
                customerEntity.phone,
                Address.from(customerEntity.getBillingAddress())
        );
    }
}
