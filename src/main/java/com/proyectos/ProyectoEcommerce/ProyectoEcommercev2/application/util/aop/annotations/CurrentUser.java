package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.aop.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Documented
public @interface CurrentUser {
}
