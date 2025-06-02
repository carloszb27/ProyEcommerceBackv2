package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.OrderEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByActiveTrue();

    @Query("select o from OrderEntity o where o.shoppingCart.user.id =:idUser and o.active = true")
    List<OrderEntity> findAllByUser(@Param("idUser") Long idUser);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update OrderEntity o set o.active=:active where o.id=:id")
    void updateOrdenVentaSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
