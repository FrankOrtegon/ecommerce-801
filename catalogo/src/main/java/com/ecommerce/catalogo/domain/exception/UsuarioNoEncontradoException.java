package com.ecommerce.catalogo.domain.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
}
