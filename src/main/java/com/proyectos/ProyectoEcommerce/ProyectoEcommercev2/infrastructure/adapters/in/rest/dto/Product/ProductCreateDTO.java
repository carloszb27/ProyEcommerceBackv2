package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import lombok.Builder;

import java.util.Date;

@Builder
public record ProductCreateDTO(String nombre, String descripcion,
                               String urlImagen, Date fechaVen,
                               Category category, Double precioLote,
                               Integer stock, Long proveedorId) {
}
