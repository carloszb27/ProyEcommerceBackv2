package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.SupplierEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaSupplierRepository extends JpaRepository<SupplierEntity, Long> {

    List<SupplierEntity> findAllByActiveTrue();

    @Modifying
    @Transactional
    @Query("update SupplierEntity c set c.active =:active where c.id =:id")
    void updateSupplierSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
