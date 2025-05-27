package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart;

import lombok.Builder;

import java.util.List;

@Builder
public record ShoppingCartUpdateDTO(Long id, double precio,
                                    List<ItemDTO> carritoItems,
                                    Long userId) {
}
