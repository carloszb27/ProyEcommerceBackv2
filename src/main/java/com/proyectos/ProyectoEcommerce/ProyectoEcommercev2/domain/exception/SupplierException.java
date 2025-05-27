package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception;

public class SupplierException extends RuntimeException {
    public SupplierException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Supplier con id: " + id +  " no encontrado";
    }
}
