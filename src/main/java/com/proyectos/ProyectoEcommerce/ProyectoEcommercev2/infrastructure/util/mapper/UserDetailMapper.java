package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail.UserDetailCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail.UserDetailDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail.UserDetailUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.UserDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(/*componentModel = "spring", uses = {OrdenVentaMapper.class}*/)
public interface UserDetailMapper {

    UserDetailMapper instancia = Mappers.getMapper(UserDetailMapper.class);

    @Mapping(target = "active", constant = "true")
    UserDetail userDetailCreateDTOAUserDetail(UserDetailCreateDTO userDetail);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "active", constant = "true")
    UserDetail userDetailDTOAUserDetail(UserDetailDTO userDetail);

    @Mapping(target = "userId", source = "user.id")
    UserDetailDTO userDetailAUserDetailDTO(UserDetail userDetail);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "active", constant = "true")
    UserDetail userDetailUpdateDTOAUserDetail(UserDetailUpdateDTO userDetail);

    @Mapping(target = "userId", source = "user.id")
    List<UserDetailDTO> listaUserDetailAListaUserDetailDTO(List<UserDetail> lista);

    UserDetail userDetailEntityAUserDetail(UserDetailEntity userDetailEntity);

    UserDetailEntity userDetailAUserDetailEntity(UserDetail userDetail);

    default Optional<UserDetail> optionalUserDetailEntityAOptionalUserDetail(Optional<UserDetailEntity> optional){
        return optional.map(this::userDetailEntityAUserDetail);
    }

    List<UserDetail> listaUserDetailEntityAListaUserDetail(List<UserDetailEntity> lista);

}
