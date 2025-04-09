package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>
{
}
