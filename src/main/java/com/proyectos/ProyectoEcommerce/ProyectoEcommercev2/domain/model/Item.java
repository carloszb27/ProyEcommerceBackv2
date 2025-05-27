package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;
import java.io.Serializable;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class Item implements Serializable {

    private Long id;
    private Product product;
    private double precio;
    private Integer cantidad;
    private boolean active;
}