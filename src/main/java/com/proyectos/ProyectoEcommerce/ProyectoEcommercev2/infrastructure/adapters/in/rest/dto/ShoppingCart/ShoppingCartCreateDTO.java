package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoppingCartCreateDTO(List<ItemDTO> carritoItems) {
}
