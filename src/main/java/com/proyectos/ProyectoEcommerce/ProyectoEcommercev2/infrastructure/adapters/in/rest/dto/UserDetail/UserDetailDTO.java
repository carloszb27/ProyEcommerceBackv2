package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.CardDetail.CardDetailDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShippingAddress.ShippingAddressDTO;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDetailDTO(Long id, String dni, CardDetailDTO cardDetail,
                            ShippingAddressDTO shippingAddress, Long userId, List<OrderDTO> ordenVentas) {
}
