package com.dolinek.fakturon.Common.Domain.Model;

import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

@Data
public class PaginationResult<T> {
    Collection<T> content;
    int perPage;
    int totalPages;
    long totalItems;
    int currentPage;

    public PaginationResult(Collection<T> content, int totalPages, long totalItems, int perPage, int currentPage) {
        this.content = content;
        this.perPage = perPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
