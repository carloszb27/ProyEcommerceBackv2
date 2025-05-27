package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier;

import lombok.Builder;

@Builder
public record SupplierUpdateDTO(Long id, String nombre, String email,
                                String numTelefono, String direccion) {
}
