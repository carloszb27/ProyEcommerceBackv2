package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Batch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BatchRepository {

    List<Batch> findAll();
    Optional<Batch> findById(Long id);
    Batch save(Batch batch);
}
