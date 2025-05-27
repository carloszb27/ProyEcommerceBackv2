package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShippingAddress;

import lombok.Builder;

@Builder
public record ShippingAddressDTO(Long id, String direccion,
                                 String ciudad, String distrito,
                                 String codigoZip, String pais) {
}
