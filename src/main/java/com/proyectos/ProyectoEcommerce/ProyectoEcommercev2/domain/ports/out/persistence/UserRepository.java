package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAllByActiveTrue();
//    User findByDni(String dni);
    Optional<User> findOneByEmail(String email);
//    User findByDetalleUsuarioId(Long idDetalleUsuario);
    void updateUserSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
