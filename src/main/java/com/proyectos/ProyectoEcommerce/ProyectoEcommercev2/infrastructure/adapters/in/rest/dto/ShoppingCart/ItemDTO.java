package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart;

import lombok.Builder;

@Builder
public record ItemDTO(Long productoId, double precio, int cantidad) {
}
