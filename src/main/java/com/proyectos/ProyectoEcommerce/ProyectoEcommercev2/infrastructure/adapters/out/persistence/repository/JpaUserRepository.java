package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.UserException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByActiveTrue();

    //public Optional<UserEntity> findByUsername(String username);

//    @Query("select u from UserEntity u where u.userDetail.dni = ?1")
//    UserEntity findByDni(String dni);

    Optional<UserEntity> findOneByEmail(String email);

    //Optional<UserEntity> findOneByUsername(String email);

//    @Query("select u from UserDetailEntity ud where ud.user.id = ?1")
//    UserEntity findByUserDetailId(Long idUserDetail);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update UserEntity u set u.active =:active where u.id =:id")
    void updateUserSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
