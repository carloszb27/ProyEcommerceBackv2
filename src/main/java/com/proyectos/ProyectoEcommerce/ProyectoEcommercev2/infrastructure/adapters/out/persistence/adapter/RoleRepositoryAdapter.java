package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Role;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.RoleRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.RoleMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final JpaRoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return RoleMapper.instancia.roleEntityARole(roleRepository.save(RoleMapper.instancia.roleARoleEntity(role)));
    }

    @Override
    public Optional<Role> findByRoleEnum(RoleEnum roleEnum) {
        return RoleMapper.instancia.optionalRoleEntityAOptionalRole(roleRepository.findByRoleEnum(roleEnum));
    }
}
