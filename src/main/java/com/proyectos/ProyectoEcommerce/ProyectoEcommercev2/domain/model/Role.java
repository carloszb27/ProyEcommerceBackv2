package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import lombok.*;

import java.io.Serializable;

@Data
//@Builder
public class Role implements Serializable {
    private Long id;
    private RoleEnum roleEnum;
}
