package com.dolinek.fakturon.Invoice.Infrastructure.Mapper;

import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateProductRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public class ProductMapper
{
    public ProductEntity toEntity(Product product)
    {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setTaxAmount(product.getTaxAmount());

        return productEntity;
    }

    public Product toProduct(ProductEntity productEntity)
    {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getPriceTax(),
                productEntity.getTaxAmount()
        );
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateProductFromDto(CreateUpdateProductRequest request, @MappingTarget ProductEntity product)
    {}
}
