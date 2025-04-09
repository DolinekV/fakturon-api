package com.dolinek.fakturon.Invoice.Domain.Service;

import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.ProductRepositoryImpl;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{
    private final ProductRepositoryImpl repository;

    public ProductService(ProductRepositoryImpl repository)
    {
        this.repository = repository;
    }

    public void createProduct(CreateUpdateProductRequest request)
    {
        Product product = Product.createNew(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getPriceTax(),
                request.getTaxAmount()
        );

        this.repository.save(product);
    }
}
