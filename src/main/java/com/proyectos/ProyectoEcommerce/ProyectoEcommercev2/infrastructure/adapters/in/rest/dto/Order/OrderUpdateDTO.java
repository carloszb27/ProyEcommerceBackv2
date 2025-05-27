package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.OrderStatus;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.MetodoPago;
import lombok.Builder;

import java.util.Date;

@Builder
public record OrderUpdateDTO(Long id, Long detalleUsuarioId, Date fechaCompra,
                             Date fechaEntrega, Long carritoId,
                             MetodoPago metodoPago, OrderStatus orderStatus) {
}
