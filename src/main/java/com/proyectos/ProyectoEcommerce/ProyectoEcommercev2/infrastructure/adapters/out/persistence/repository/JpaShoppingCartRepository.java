package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.ShoppingCartEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    List<ShoppingCartEntity> findAllByActiveTrue();

    @Query("select c from ShoppingCartEntity c where c.user.id =:idUser and c.active = true")
    Optional<ShoppingCartEntity> findByUserId(@Param("idUser") Long idUser);

    boolean existsShoppingCartEntityByUserId(Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update ShoppingCartEntity c set c.active =:active where c.id =:id")
    void updateShoppingCartEntitySetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
