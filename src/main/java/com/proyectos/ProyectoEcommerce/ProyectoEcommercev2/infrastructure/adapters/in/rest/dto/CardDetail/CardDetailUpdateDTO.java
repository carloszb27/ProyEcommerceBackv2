package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.CardDetail;

import lombok.Builder;

import java.util.Date;

@Builder
public record CardDetailUpdateDTO(Long id, String numeroTarjeta, String cvv,
                                  Date fechaVen, String password) {
}
