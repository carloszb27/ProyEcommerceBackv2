package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Auth;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.AuthResponse;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;

import java.io.IOException;

public interface AuthService {
    AuthResponse register(User request);
    AuthResponse authenticate(Auth request) throws IOException;
}
