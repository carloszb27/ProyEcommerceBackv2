package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Supplier;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier.SupplierCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier.SupplierDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier.SupplierUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.SupplierEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SupplierMapper {

    SupplierMapper instancia = Mappers.getMapper(SupplierMapper.class);

    @Mapping(target = "active", constant = "true")
    Supplier supplierCreateDTOASupplier(SupplierCreateDTO Supplier);

    @Mapping(target = "active", constant = "true")
    Supplier supplierDTOASupplier(SupplierDTO Supplier);

    SupplierDTO supplierASupplierDTO(Supplier Supplier);

    @Mapping(target = "active", constant = "true")
    Supplier supplierUpdateDTOASupplier(SupplierUpdateDTO Supplier);

    List<SupplierDTO> listaSupplierAListaSupplierDTO(List<Supplier> lista);

    Supplier supplierEntityASupplier(SupplierEntity supplierEntity);

    SupplierEntity supplierASupplierEntity(Supplier supplier);

    default Optional<Supplier> optionalSupplierEntityAOptionalSupplier(Optional<SupplierEntity> optional){
        return optional.map(this::supplierEntityASupplier);
    }

    List<Supplier> listaSupplierEntityAListaSupplier(List<SupplierEntity> lista);

}
