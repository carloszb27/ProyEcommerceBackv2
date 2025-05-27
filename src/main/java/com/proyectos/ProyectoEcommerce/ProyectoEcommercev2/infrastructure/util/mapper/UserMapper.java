package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.User.UserDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.User.UserUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    UserMapper instancia = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "active", constant = "true")
    User userDTOAUser(UserDTO userDTO);

    UserDTO userAUserDTO(User user);

    @Mapping(target = "active", constant = "true")
    User userCreateDTOAUser(UserCreateDTO userCreateDTO);

    @Mapping(target = "active", constant = "true")
    User userUpdateDTOAUser(UserUpdateDTO userUpdateDTO);

    List<UserDTO> listaUserAListaUserDTO(List<User> lista);

    @Mapping(target = "active", constant = "true")
    List<User> listaUserDTOAListaUser(List<UserDTO> lista);

    User userEntityAUser(UserEntity userEntity);

    UserEntity userAUserEntity(User user);

    default Optional<User> optionalUserEntityAOptionalUser(Optional<UserEntity> optional){
        return optional.map(this::userEntityAUser);
    }

    List<User> listaUserEntityAListaUser(List<UserEntity> lista);

}
