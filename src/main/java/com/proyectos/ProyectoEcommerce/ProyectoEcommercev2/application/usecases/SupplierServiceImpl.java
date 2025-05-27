package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.SupplierService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Supplier;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.SupplierException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public List<Supplier> listarSuppliers() {
        List<Supplier> lista = supplierRepository.findAllByActiveTrue();
        return lista;
    }

    @Override
    public Supplier listarSupplierPorId(Long id) throws SupplierException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierException(SupplierException.NotFoundException(id)));
        return supplier;
    }

    @Override
    public Supplier registrarSupplier(Supplier supplier) {
        Supplier nuevoSupplier = supplierRepository.save(supplier);
        return nuevoSupplier;
    }

    @Override
    public Supplier actualizarSupplier(Supplier supplier) throws SupplierException {
        Supplier supplierExiste = supplierRepository.findById(supplier.getId())
                .orElseThrow(() -> new SupplierException(
                        SupplierException.NotFoundException(supplier.getId())));
        return supplierRepository.save(supplier);
    }

    @Override
    public String eliminarSupplier(Long id) throws SupplierException {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierException(SupplierException.NotFoundException(id)));
        supplierRepository.updateSupplierSetActiveForId(false, id);
        return "El supplier se ha eliminado correctamente";
    }
}
