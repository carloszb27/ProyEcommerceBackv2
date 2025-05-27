package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import lombok.Builder;

import java.util.Date;

@Builder
public record ProductUpdateDTO(Long id, String nombre, String descripcion,
                               Double precio, String urlImagen, Date fechaVen,
                               Category category, Integer stock, Double descuento) {
}
