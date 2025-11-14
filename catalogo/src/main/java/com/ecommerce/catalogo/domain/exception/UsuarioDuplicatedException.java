package com.ecommerce.catalogo.domain.exception;

public class UsuarioDuplicatedException extends RuntimeException {
    public UsuarioDuplicatedException(String message) {
        super(message);
    }
}
