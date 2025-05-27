package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
//@Builder
public class User implements Serializable {

    private Long id;
    private String firstname;
    private String lastname;
    private String cellphone;
    private Date fechaNacimiento;
    private String email;
    private String password;
    private Long detalleUsuarioId;
    private boolean active;
    private Set<Role> roles;
}
