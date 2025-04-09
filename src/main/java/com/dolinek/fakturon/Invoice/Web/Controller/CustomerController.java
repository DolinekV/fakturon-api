package com.dolinek.fakturon.Invoice.Web.Controller;

import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Model.Customer;
import com.dolinek.fakturon.Invoice.Domain.Service.CustomerService;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.CustomerRepositoryImpl;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateCustomerRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepositoryImpl customerRepository;

    private final CustomerService customerService;

    public CustomerController(CustomerRepositoryImpl customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    PaginationResult<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerRequest customer)
    {
        customerService.createCustomer(customer);

        return new ResponseEntity<>(new HashMap<>(), HttpStatus.CREATED);
    }
}
