package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.CardDetail;

import lombok.Builder;

import java.util.Date;

@Builder
public record CardDetailCreateDTO(String numeroTarjeta, String cvv,
                                  Date fechaVen, String password) {
}
