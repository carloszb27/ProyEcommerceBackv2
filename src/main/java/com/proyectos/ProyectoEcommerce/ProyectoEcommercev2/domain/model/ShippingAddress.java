package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;

import java.io.Serializable;

@Data
//@Builder
public class ShippingAddress implements Serializable {

    private Long id;
    private String direccion;
    private String ciudad;
    private String distrito;
    private String codigoZip;
    private String pais;
    private boolean active;
}
