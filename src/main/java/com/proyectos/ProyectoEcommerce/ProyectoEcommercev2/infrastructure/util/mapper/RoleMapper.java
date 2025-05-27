package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Role;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RoleMapper {

    RoleMapper instancia = Mappers.getMapper(RoleMapper.class);

    Role roleEntityARole(RoleEntity roleEntity);

    RoleEntity roleARoleEntity(Role role);

    default Optional<Role> optionalRoleEntityAOptionalRole(Optional<RoleEntity> optional){
        return optional.map(this::roleEntityARole);
    }

    List<Role> listaRoleEntityAListaRole(List<RoleEntity> lista);

    List<RoleEntity> listaRoleAListaRoleEntity(List<Role> lista);
}
