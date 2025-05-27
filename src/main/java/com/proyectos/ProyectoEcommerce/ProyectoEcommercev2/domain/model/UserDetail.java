package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Data
//@Builder
public class UserDetail implements Serializable {

    private Long id;
    private String dni;
    private CardDetail cardDetail;
    private ShippingAddress shippingAddress;
    private User user;
    private boolean active;
}