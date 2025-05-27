package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.CardDetail.CardDetailCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShippingAddress.ShippingAddressCreateDTO;
import lombok.Builder;

@Builder
public record UserDetailCreateDTO(String dni, CardDetailCreateDTO cardDetail,
                                  ShippingAddressCreateDTO shippingAddress) {
}
