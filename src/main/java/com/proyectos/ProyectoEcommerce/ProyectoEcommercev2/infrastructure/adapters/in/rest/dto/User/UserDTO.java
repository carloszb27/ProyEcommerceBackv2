package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.User;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserDTO(Long id, String firstname, String lastname,
                      String email, String cellphone,
                      Date fechaNacimiento, Long detalleUsuarioId) {
}
