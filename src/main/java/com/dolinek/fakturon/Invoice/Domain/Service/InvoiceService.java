package com.dolinek.fakturon.Invoice.Domain.Service;

import com.dolinek.fakturon.Invoice.Domain.Interface.CustomerRepository;
import com.dolinek.fakturon.Invoice.Domain.Interface.InvoiceRepository;
import com.dolinek.fakturon.Invoice.Domain.Interface.ProductRepository;
import com.dolinek.fakturon.Invoice.Domain.Model.Customer;
import com.dolinek.fakturon.Invoice.Domain.Model.Invoice;
import com.dolinek.fakturon.Invoice.Domain.Model.InvoiceProduct;
import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.InvoiceProductMapper;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.CustomerJpaRepository;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.InvoiceJpaRepository;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.ProductJpaRepository;
import com.dolinek.fakturon.Invoice.Web.Dto.InvoiceProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceJpaRepository invoiceRepository;
    private final ProductJpaRepository productRepository;
    private final CustomerJpaRepository customerRepository;
    private final InvoiceProductMapper invoiceProductMapper;

    public InvoiceService(InvoiceJpaRepository invoiceRepository,
                          ProductJpaRepository productRepository,
                          CustomerJpaRepository customerRepository,
                          InvoiceProductMapper invoiceProductMapper) {
        this.invoiceRepository = invoiceRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.invoiceProductMapper = invoiceProductMapper;
    }
}