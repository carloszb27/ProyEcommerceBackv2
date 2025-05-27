package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;

import java.util.List;
import java.util.Optional;

public interface UserDetailService {

    List<UserDetail> listarUserDetails();
    UserDetail listarUserDetailPorId(Long id);
    UserDetail registrarUserDetail(UserDetail userDetail);
    UserDetail actualizarUserDetail(Long id, UserDetail userDetail);
    void validarUserDetail(UserDetail UserDetail);
    Optional<UserDetail> listarDetallePorUserActual();
}
