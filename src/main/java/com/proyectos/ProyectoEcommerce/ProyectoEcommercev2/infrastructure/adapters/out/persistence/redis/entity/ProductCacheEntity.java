package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.redis.entity;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.BatchEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@RedisHash("Product")
@Data
public class ProductCacheEntity implements Serializable {

    @Id
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String urlImagen;
    private Date fechaVen;
    private Category category;
    private BatchEntity batch;
    private double descuento;
    private boolean active;
}
