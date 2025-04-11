package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Common.Domain.Exception.EntityNotFoundException;
import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Interface.InvoiceRepository;

import com.dolinek.fakturon.Invoice.Domain.Model.Invoice;
import com.dolinek.fakturon.Invoice.Domain.Model.InvoiceProduct;
import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.CustomerEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceProductEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.CustomerMapper;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.InvoiceMapper;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.InvoiceProductMapper;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.ProductMapper;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateInvoiceRequest;
import com.dolinek.fakturon.Invoice.Web.Dto.InvoiceProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {
    private final InvoiceJpaRepository jpaRepository;
    private final CustomerJpaRepository customerRepository;
    private final ProductJpaRepository productRepository;


    private final ProductMapper productMapper;
    private final CustomerMapper customerMapper;

    public PaginationResult<Invoice> findAll(Pageable pageable) {
        Page<InvoiceEntity> invoiceEntities = this.jpaRepository.findAll(pageable);

        List<Invoice> invoices = new ArrayList<>();

        for (InvoiceEntity invoiceEntity : invoiceEntities.getContent()) {
            List<InvoiceProduct> products = invoiceEntity.getInvoiceProducts()
                    .stream()
                    .map(invoiceProductEntity -> {
                        ProductEntity productEntity = invoiceProductEntity.getProduct();
                        if (productEntity != null) {
                            return InvoiceProduct.fromPredefinedProduct(
                                    invoiceProductEntity.getId(),
                                    productMapper.toProduct(productEntity)
                            );
                        } else {
                            return InvoiceProduct.fromCustomProduct(
                                    invoiceProductEntity.getId(),
                                    invoiceProductEntity.getCustomName(),
                                    invoiceProductEntity.getCustomDescription(),
                                    invoiceProductEntity.getCustomPrice(),
                                    invoiceProductEntity.getCustomPriceTax(),
                                    invoiceProductEntity.getCustomTaxAmount()
                            );
                        }
                    })
                    .toList();

            invoices.add(
                    new Invoice(
                            invoiceEntity.getId(),
                            customerMapper.toCustomer(invoiceEntity.getCustomer()),
                            products
                    )
            );
        }

        return new PaginationResult<>(invoices, invoiceEntities.getTotalPages(), invoiceEntities.getTotalElements(), pageable.getPageSize(), pageable.getPageNumber());
    }

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
