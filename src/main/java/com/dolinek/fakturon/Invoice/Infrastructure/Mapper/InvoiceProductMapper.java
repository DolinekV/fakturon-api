package com.dolinek.fakturon.Invoice.Infrastructure.Mapper;

import com.dolinek.fakturon.Invoice.Domain.Model.Invoice;
import com.dolinek.fakturon.Invoice.Domain.Model.InvoiceProduct;
import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.InvoiceProductEntity;
import com.dolinek.fakturon.Invoice.Web.Dto.InvoiceProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceProductMapper {

    public InvoiceProductDTO toDTO(InvoiceProduct invoiceProduct) {
        InvoiceProductDTO dto = new InvoiceProductDTO();
        if (invoiceProduct.getProduct() != null) {
            dto.setProductId(invoiceProduct.getProduct().getId());
        } else {
            dto.setCustomName(invoiceProduct.getCustomName());
        }
        dto.setPrice(invoiceProduct.getPrice());
        return dto;
    }

    public InvoiceProduct toEntity(InvoiceProductDTO dto, Invoice invoice, Product product) {
        InvoiceProduct invoiceProduct = new InvoiceProduct(
                invoice,
                product,
                dto.getCustomName(),
                dto.getPrice(),
                dto.getPriceTax(),
                dto.getTaxAmount()
        );

        return invoiceProduct;
    }

    public List<InvoiceProductDTO> toDTOList(List<InvoiceProduct> invoiceProducts) {
        return invoiceProducts.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
