package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.CardDetail;

import lombok.Builder;

@Builder
public record CardDetailDTO(Long id, String numeroTarjeta) {
}
