package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception;

public class ShoppingCartException extends RuntimeException {
    public ShoppingCartException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "ShoppingCart con id: " + id +  " no encontrado";
    }
}
