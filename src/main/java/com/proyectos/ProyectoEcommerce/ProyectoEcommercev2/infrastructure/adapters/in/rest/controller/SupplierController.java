package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.SupplierService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier.SupplierCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier.SupplierDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Supplier.SupplierUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.SupplierMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Supplier resource")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("")
    public ResponseEntity<?> listadoSuppliers(){
        List<SupplierDTO> listaDTO = SupplierMapper.instancia.listaSupplierAListaSupplierDTO(supplierService.listarSuppliers());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarSupplierPorId(@PathVariable Long id){
        SupplierDTO supplierDTO = SupplierMapper.instancia.supplierASupplierDTO(supplierService.listarSupplierPorId(id));
        return CustomResponseBuilder.getInstance().crearResponse(supplierDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarSupplier(@Valid @RequestBody SupplierCreateDTO supplier){
        SupplierDTO supplierDTO = SupplierMapper.instancia.supplierASupplierDTO(supplierService.registrarSupplier(SupplierMapper.instancia.supplierCreateDTOASupplier(supplier)));
        return CustomResponseBuilder.getInstance().crearResponse(supplierDTO, true, supplierDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarSupplier(@Valid @RequestBody SupplierUpdateDTO supplier){
        SupplierDTO supplierDTO = SupplierMapper.instancia.supplierASupplierDTO(supplierService.actualizarSupplier(SupplierMapper.instancia.supplierUpdateDTOASupplier(supplier)));
        return CustomResponseBuilder.getInstance().crearResponse(supplierDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSupplier(@PathVariable Long id){
        String mensaje = supplierService.eliminarSupplier(id);
        return CustomResponseBuilder.getInstance().crearResponse(mensaje);
    }

}
