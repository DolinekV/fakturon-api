package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Common.Domain.Exception.EntityNotFoundException;
import com.dolinek.fakturon.Invoice.Domain.Interface.InvoiceRepository;

import com.dolinek.fakturon.Invoice.Infrastructure.Entity.CustomerEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceProductEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateInvoiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {
    private final InvoiceJpaRepository jpaRepository;
    private final CustomerJpaRepository customerRepository;
    private final ProductJpaRepository productRepository;

    public void save(CreateUpdateInvoiceRequest request) {
        CustomerEntity customerEntity = this.customerRepository.findById(request.getCustomerId()).orElseThrow(
                () -> new EntityNotFoundException("Customer", request.getCustomerId())
        );
        List<InvoiceProductEntity> invoiceProductEntities = new ArrayList<>();

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        for (Long productId : request.getProductIds()) {
            ProductEntity productEntity = this.productRepository.findById(productId).orElseThrow(
                    () -> new EntityNotFoundException("Product", productId)
            );

            InvoiceProductEntity invoiceProductEntity = new InvoiceProductEntity();
            invoiceProductEntity.setProduct(productEntity);
            invoiceProductEntity.setInvoice(invoiceEntity);

            invoiceProductEntities.add(invoiceProductEntity);
        }

        invoiceEntity.setCustomer(customerEntity);
        invoiceEntity.setInvoiceProducts(invoiceProductEntities);

        this.jpaRepository.save(invoiceEntity);
    }
}
