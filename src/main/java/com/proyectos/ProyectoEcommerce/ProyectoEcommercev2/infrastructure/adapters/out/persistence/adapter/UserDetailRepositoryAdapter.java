package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.UserDetailRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.UserDetailEntity;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.UserDetailMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaUserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDetailRepositoryAdapter implements UserDetailRepository {

    private final JpaUserDetailRepository userDetailRepository;

    @Override
    public List<UserDetail> findAll() {
        return UserDetailMapper.instancia.listaUserDetailEntityAListaUserDetail(userDetailRepository.findAll());
    }

    @Override
    public Optional<UserDetail> findById(Long id) {
        return UserDetailMapper.instancia.optionalUserDetailEntityAOptionalUserDetail(userDetailRepository.findById(id));
    }

    @Override
    public UserDetail save(UserDetail userDetail) {

        UserDetailEntity userDetailEntity = UserDetailMapper.instancia.userDetailAUserDetailEntity(userDetail);
        UserDetailEntity userDetailEntity1 = userDetailRepository.save(userDetailEntity);

        return UserDetailMapper.instancia.userDetailEntityAUserDetail(userDetailEntity1);
    }

    @Override
    public Optional<UserDetail> findByUserId(Long idUser) {

        Optional<UserDetailEntity> userDetailEntity = userDetailRepository.findByUserId(idUser);

        return UserDetailMapper.instancia.optionalUserDetailEntityAOptionalUserDetail(userDetailEntity);
    }


}
