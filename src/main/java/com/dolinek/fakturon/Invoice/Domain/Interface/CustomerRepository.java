package com.dolinek.fakturon.Invoice.Domain.Interface;

import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Model.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepository {
    PaginationResult<Customer> findAll(Pageable pageable);

    boolean existsByEmail(String email);
}
