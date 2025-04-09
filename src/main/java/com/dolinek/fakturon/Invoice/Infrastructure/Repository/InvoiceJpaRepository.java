package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceJpaRepository extends JpaRepository<InvoiceEntity, Long>
{
}
