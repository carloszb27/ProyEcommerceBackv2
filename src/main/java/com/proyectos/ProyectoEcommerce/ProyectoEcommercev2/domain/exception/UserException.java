package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "User con id: " + id +  " no encontrado";
    }
}
