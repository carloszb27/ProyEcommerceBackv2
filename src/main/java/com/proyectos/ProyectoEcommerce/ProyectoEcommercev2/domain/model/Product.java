package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class Product implements Serializable {

    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String urlImagen;
    private Date fechaVen;
    private Category category;
    private Batch batch;
    private double descuento;
    private boolean active;
}
