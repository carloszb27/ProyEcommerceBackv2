package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception;

public class ProductException extends RuntimeException {
    public ProductException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Product con id: " + id +  " no encontrado";
    }
}
