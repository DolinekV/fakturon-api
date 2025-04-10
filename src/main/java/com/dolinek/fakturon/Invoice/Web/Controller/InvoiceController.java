package com.dolinek.fakturon.Invoice.Web.Controller;

import com.dolinek.fakturon.Invoice.Infrastructure.Repository.InvoiceRepositoryImpl;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateInvoiceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceRepositoryImpl repository;

    public InvoiceController(InvoiceRepositoryImpl repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody CreateUpdateInvoiceRequest request) {
        this.repository.save(request);

        return new ResponseEntity<>(new HashMap<>(), HttpStatus.CREATED);
    }
}
