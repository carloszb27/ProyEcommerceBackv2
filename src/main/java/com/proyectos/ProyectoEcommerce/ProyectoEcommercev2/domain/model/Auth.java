package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth implements Serializable {

    private String email;
    private String password;
}
