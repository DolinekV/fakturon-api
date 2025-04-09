package com.dolinek.fakturon.Invoice.Infrastructure.Mapper;

import com.dolinek.fakturon.Invoice.Domain.Model.Invoice;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceEntity;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper
{
    public InvoiceEntity toEntity(Invoice invoice)
    {
        InvoiceEntity invoiceEntity = new InvoiceEntity();

        return invoiceEntity;
    }

    public Invoice toInvoice(InvoiceEntity invoiceEntity)
    {
        Invoice invoice = new Invoice();

        return invoice;
    }
}
