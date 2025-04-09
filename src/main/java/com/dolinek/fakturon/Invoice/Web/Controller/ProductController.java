package com.dolinek.fakturon.Invoice.Web.Controller;

import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Domain.Service.ProductService;
import com.dolinek.fakturon.Invoice.Infrastructure.Repository.ProductRepositoryImpl;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateProductRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/product")
@RestController
public class ProductController
{
    private final ProductRepositoryImpl repository;

    private final ProductService service;

    public ProductController(ProductRepositoryImpl repository, ProductService service)
    {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public PaginationResult<Product> getProductList(Pageable pageable)
    {
        return this.repository.findAll(pageable);
    }

    @PostMapping
    public void createProduct(@Valid @RequestBody CreateUpdateProductRequest request)
    {
        this.service.createProduct(request);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@Valid @RequestBody CreateUpdateProductRequest request, @PathVariable Long id)
    {
        this.repository.updatePartial(id, request);
    }
}
