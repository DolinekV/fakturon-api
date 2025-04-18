package com.dolinek.fakturon.Invoice.Infrastructure.Mapper;

import com.dolinek.fakturon.Invoice.Domain.Model.InvoiceProduct;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceProductEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import org.springframework.stereotype.Component;


@Component
public class InvoiceProductMapper {
    private final ProductMapper productMapper;

    public InvoiceProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public InvoiceProduct toInvoiceProduct(InvoiceProductEntity invoiceProductEntity)
    {
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
    }
}
