package com.dolinek.fakturon.Common.Domain.Exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String item, Long id) {
        super(String.format("%s with id %s not found", item, id));
    }
}
