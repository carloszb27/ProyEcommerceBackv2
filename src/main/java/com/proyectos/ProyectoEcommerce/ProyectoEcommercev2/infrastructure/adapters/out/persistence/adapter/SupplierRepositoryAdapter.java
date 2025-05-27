package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Supplier;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.SupplierRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.SupplierMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SupplierRepositoryAdapter implements SupplierRepository {

    private final JpaSupplierRepository supplierRepository;

    @Override
    public Supplier save(Supplier supplier) {
        return SupplierMapper.instancia.supplierEntityASupplier(supplierRepository.save(SupplierMapper.instancia.supplierASupplierEntity(supplier)));
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return SupplierMapper.instancia.optionalSupplierEntityAOptionalSupplier(supplierRepository.findById(id));
    }

    @Override
    public List<Supplier> findAllByActiveTrue() {
        return SupplierMapper.instancia.listaSupplierEntityAListaSupplier(supplierRepository.findAllByActiveTrue());
    }

    @Override
    public void updateSupplierSetActiveForId(boolean active, Long id) {
        supplierRepository.updateSupplierSetActiveForId(active, id);
    }



}
