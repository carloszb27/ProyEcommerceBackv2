package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.OrderStatus;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.MetodoPago;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class Order implements Serializable {

    private Long id;
    private UserDetail userDetail;
    private Date fechaCompra;
    private Date fechaEntrega;
    private ShoppingCart shoppingCart;
    private MetodoPago metodoPago;
    private OrderStatus orderStatus;
    private boolean active;
}
