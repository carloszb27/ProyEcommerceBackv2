package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;
import java.io.Serializable;

@Data
//@Builder
public class Batch implements Serializable {

    private Long id;
    private double precio;
    private int stock;
    private Supplier supplier;
    private boolean active;
}
