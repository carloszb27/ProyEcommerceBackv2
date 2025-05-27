package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
//@Builder
public class CardDetail implements Serializable {

    private Long id;
    private String numeroTarjeta;
    private String cvv;
    private Date fechaVen;
    private String password;
    private boolean active;
}
