package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Interface.CustomerRepository;
import com.dolinek.fakturon.Invoice.Domain.Model.Customer;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.CustomerEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.CustomerMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerJpaRepository jpaRepository;
    private final CustomerMapper customerMapper;

    public CustomerRepositoryImpl(CustomerJpaRepository customerJpaRepository, CustomerMapper customerMapper) {
        this.jpaRepository = customerJpaRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public PaginationResult<Customer> findAll(Pageable pageable) {
        Page<CustomerEntity> customerEntities = this.jpaRepository.findAll(pageable);

        return new PaginationResult<>(customerEntities.map(customerMapper::toCustomer).toList(), customerEntities.getTotalPages(), customerEntities.getTotalElements(), pageable.getPageSize(), pageable.getPageNumber());
    }

    public void save(Customer customer)
    {
        CustomerEntity customerEntity = this.jpaRepository.save(this.customerMapper.toEntity(customer));

        this.customerMapper.toCustomer(customerEntity);
    }

    public boolean existsByEmail(String email)
    {
        return this.jpaRepository.existsByEmail(email);
    }
}
