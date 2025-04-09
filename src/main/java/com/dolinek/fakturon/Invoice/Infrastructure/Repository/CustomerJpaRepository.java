package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Invoice.Infrastructure.Entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);
}
