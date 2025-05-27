package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.CartStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tbl_carrito")
//@Data
@Getter
@Setter
@EqualsAndHashCode(exclude = {"items", "user"}, callSuper = false)
@ToString(exclude = {"items", "user"})

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartEntity extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "precioTotal", length = 100, nullable = false)
    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero
    private double precio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "carrito", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Los items son obligatorios")
    private List<ItemEntity> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @NotNull(message = "El user es obligatorio")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private CartStatus estado;

    @Column(name = "active")
    private boolean active = true;
}