package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;
import java.io.Serializable;

@Data
//@Builder
public class Supplier implements Serializable {

    private Long id;
    private String nombre;
    private String email;
    private String numTelefono;
    private String direccion;
    private boolean active;
}
