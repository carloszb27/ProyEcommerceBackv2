package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Orden de venta con id: " + id +  " no encontrado";
    }

}
