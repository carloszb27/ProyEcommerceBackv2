package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_carrito_item")

@Getter
@Setter
@EqualsAndHashCode(exclude = {"product"}, callSuper = false)
@ToString(exclude = {"product"})
//@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = "carrito")
public class ItemEntity extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCarrito")
    @JsonBackReference
    private ShoppingCart carrito;
*/
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private ProductEntity product;

    @Column(name = "precioItem")
    @PositiveOrZero
    private double precio;

    @Column(name = "cantidadItem")
    @PositiveOrZero
    private Integer cantidad;

    @Column(name = "active")
    private boolean active = true;
}