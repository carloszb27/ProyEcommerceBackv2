package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailRepository {

    List<UserDetail> findAll();
    Optional<UserDetail> findById(Long id);
    UserDetail save(UserDetail userDetail);
    Optional<UserDetail> findByUserId(Long idUser);
}
