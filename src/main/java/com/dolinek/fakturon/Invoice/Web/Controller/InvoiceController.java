package com.dolinek.fakturon.Invoice.Web.Controller;

import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Model.Invoice;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.InvoiceRepositoryImpl;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateInvoiceRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceRepositoryImpl repository;

    public InvoiceController(InvoiceRepositoryImpl repository) {
        this.repository = repository;
    }

    @GetMapping
    public PaginationResult<Invoice> getInvoices(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody CreateUpdateInvoiceRequest request) {
        this.repository.save(request);

        return new ResponseEntity<>(new HashMap<>(), HttpStatus.CREATED);
    }
}
