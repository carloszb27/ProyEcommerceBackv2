package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class File {

    private UUID id;
    private String name;
    private String type;
    private byte[] data;
}
