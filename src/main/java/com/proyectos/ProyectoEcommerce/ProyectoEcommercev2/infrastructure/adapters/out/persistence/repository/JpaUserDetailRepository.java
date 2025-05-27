package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserDetailRepository extends JpaRepository<UserDetailEntity, Long> {

    @Query("select d from UserDetailEntity d where d.user.id = :idUser and d.active = true")
    Optional<UserDetailEntity> findByUserId(Long idUser);

}
