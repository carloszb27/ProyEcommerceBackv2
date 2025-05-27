package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Supplier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository {

    Supplier save(Supplier supplier);
    Optional<Supplier> findById(Long id);
    List<Supplier> findAllByActiveTrue();
    void updateSupplierSetActiveForId(@Param("active") boolean active, @Param("id") Long id);
}
