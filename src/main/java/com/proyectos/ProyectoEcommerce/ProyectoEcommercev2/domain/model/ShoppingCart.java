package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.CartStatus;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class ShoppingCart implements Serializable {

    private Long id;
    private double precio;
    private List<Item> items;
    private User user;
    private CartStatus estado;
    private boolean active;
}