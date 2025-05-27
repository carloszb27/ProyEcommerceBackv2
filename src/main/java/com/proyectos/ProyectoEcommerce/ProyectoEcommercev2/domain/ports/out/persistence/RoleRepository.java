package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Role;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {

    Role save(Role role);
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
}
