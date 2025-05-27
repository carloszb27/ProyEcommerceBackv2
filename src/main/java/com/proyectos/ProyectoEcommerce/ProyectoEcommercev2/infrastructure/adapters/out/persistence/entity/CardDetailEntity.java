package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_detallePago")

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString()
//@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDetailEntity extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 50)
    private Long id;

    @Column(name = "numero_tarjeta", length = 50, nullable = false)
    @NotBlank(message = "El numero de la tarjeta es obligatorio")
    private String numeroTarjeta;

    @Column(name = "cvv", length = 10, nullable = false)
    @NotBlank(message = "El codigo cvv es obligatorio")
    private String cvv;

    @Column(name = "fechaVen", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(value = TemporalType.DATE)
    private Date fechaVen;

    @Column(name = "password", length = 50, nullable = false)
    @NotBlank(message = "El password es obligatorio")
    private String password;

    @Column(name = "active")
    private boolean active = true;
}
