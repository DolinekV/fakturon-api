package com.dolinek.fakturon.Invoice.Infrastructure.Repository;

import com.dolinek.fakturon.Common.Domain.Model.PaginationResult;
import com.dolinek.fakturon.Invoice.Domain.Interface.ProductRepository;
import com.dolinek.fakturon.Invoice.Domain.Model.Product;
import com.dolinek.fakturon.Invoice.Infrastructure.Entity.ProductEntity;
import com.dolinek.fakturon.Invoice.Infrastructure.Mapper.ProductMapper;
import com.dolinek.fakturon.Invoice.Web.Dto.CreateUpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository
{
    private final ProductJpaRepository repository;

    private final ProductMapper mapper;

    public ProductRepositoryImpl(ProductJpaRepository repository, ProductMapper mapper)
    {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PaginationResult<Product> findAll(Pageable pageable)
    {
        Page<ProductEntity> customerEntities = this.repository.findAll(pageable);

        return new PaginationResult<>(customerEntities.map(mapper::toProduct).toList(), customerEntities.getTotalPages(), customerEntities.getTotalElements(), pageable.getPageSize(), pageable.getPageNumber());
    }

    public void save(Product product)
    {
        this.repository.save(this.mapper.toEntity(product));
    }

    public void updatePartial(Long id, CreateUpdateProductRequest request)
    {
        ProductEntity productEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        mapper.updateProductFromDto(request, productEntity);

        repository.save(productEntity);
    }
}
