package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.MetodoPago;
import lombok.Builder;

@Builder
public record OrderCreateDTO(Long carritoId, MetodoPago metodoPago) {
}
