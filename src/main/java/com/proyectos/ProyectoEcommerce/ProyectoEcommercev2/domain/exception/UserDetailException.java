package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception;

public class UserDetailException extends RuntimeException {
    public UserDetailException(String message) {
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Detalle de usuario con id: " + id +  " no encontrado";
    }
}
