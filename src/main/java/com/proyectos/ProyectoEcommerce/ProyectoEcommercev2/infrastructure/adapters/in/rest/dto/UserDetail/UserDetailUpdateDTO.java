package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.CardDetail.CardDetailUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShippingAddress.ShippingAddressUpdateDTO;
import lombok.Builder;

@Builder
public record UserDetailUpdateDTO(Long id, String dni, CardDetailUpdateDTO cardDetail,
                                  ShippingAddressUpdateDTO shippingAddress, Long userId) {
}
