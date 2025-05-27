package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> listarSuppliers();
    Supplier listarSupplierPorId(Long id);
    Supplier registrarSupplier(Supplier supplier);
    Supplier actualizarSupplier(Supplier supplier);
    String eliminarSupplier(Long id);
}
