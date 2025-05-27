package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;
import java.io.Serializable;

@Data
//@Builder
public class Permission implements Serializable {

    private Long id;
    private String nombre;
}
