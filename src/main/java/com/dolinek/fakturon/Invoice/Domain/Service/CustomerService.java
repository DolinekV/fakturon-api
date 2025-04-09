package com.dolinek.fakturon.Invoice.Domain.Service;

import com.dolinek.fakturon.Common.Domain.Mapper.AddressMapper;
import com.dolinek.fakturon.Common.Domain.Model.Address;
import com.dolinek.fakturon.Invoice.Domain.Exception.EmailAlreadyTakenException;
import com.dolinek.fakturon.Invoice.Domain.Model.Customer;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.CustomerMapper;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.CustomerRepositoryImpl;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateCustomerRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private final CustomerRepositoryImpl repository;

    private final CustomerMapper mapper;

    private final AddressMapper addressMapper;

    public CustomerService(CustomerRepositoryImpl repository, CustomerMapper mapper, AddressMapper addressMapper)
    {
        this.repository = repository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
    }

    public void createCustomer(CreateCustomerRequest request) throws EmailAlreadyTakenException
    {
        if (this.repository.existsByEmail(request.getEmail()))
        {
            throw new EmailAlreadyTakenException("email.exists");
        }

        Customer customer = Customer.createNew(
                request.getName(),
                request.getCin(),
                request.getTin(),
                request.getEmail(),
                request.getPhone(),
                Address.from(request.getAddress())
        );

        repository.save(customer);
    }
}
