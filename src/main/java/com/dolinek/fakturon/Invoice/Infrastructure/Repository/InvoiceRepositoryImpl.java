package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Common.Domain.Exception.EntityNotFoundException;
import com.dolinek.fakturon.Invoice.Domain.Interface.InvoiceRepository;

import com.dolinek.fakturon.Invoice.Infrastructure.Entity.CustomerEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceProductEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateInvoiceRequest;
import com.dolinek.fakturon.Invoice.Web.Dto.InvoiceProductDTO;
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

        for (InvoiceProductDTO productDTO : request.getProducts()) {
            ProductEntity productEntity = null;
            InvoiceProductEntity invoiceProductEntity = new InvoiceProductEntity();

            if (productDTO.getProductId() != null) {
                productEntity = this.productRepository.findById(productDTO.getProductId()).orElseThrow(
                        () -> new EntityNotFoundException("Product", productDTO.getProductId())
                );
            } else {
                invoiceProductEntity.setCustomName(productDTO.getCustomName());
                invoiceProductEntity.setCustomDescription(productDTO.getCustomDescription());
                invoiceProductEntity.setCustomPrice(productDTO.getPrice());
                invoiceProductEntity.setCustomPriceTax(productDTO.getPriceTax());
                invoiceProductEntity.setCustomTaxAmount(productDTO.getTaxAmount());
            }

            if (productEntity != null) {
                invoiceProductEntity.setProduct(productEntity);
            }

            invoiceProductEntity.setInvoice(invoiceEntity);

            invoiceProductEntities.add(invoiceProductEntity);
        }

        invoiceEntity.setCustomer(customerEntity);
        invoiceEntity.setInvoiceProducts(invoiceProductEntities);

        this.jpaRepository.save(invoiceEntity);
    }
}
