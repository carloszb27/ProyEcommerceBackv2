package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;

import java.util.List;

public interface UserService {

    List<User> listarUsers();
    User listarUserPorId(Long id);
    User registrarUser(User user);
    User actualizarUser(Long id, User user);
    String eliminarUser(Long id);
}
