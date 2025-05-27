package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Order;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;


@Mapper(/*componentModel = MappingConstants.ComponentModel.SPRING*/)
public interface OrderMapper {

    OrderMapper instancia = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "shoppingCart.id", source = "carritoId")
    @Mapping(target = "active", constant = "true")
    Order orderCreateDTOAOrder(OrderCreateDTO Order);

    @Mapping(target = "userDetail.id", source = "detalleUsuarioId")
    @Mapping(target = "shoppingCart.id", source = "carritoId")
    @Mapping(target = "active", constant = "true")
    Order orderDTOAOrder(OrderDTO Order);

    @Mapping(target = "detalleUsuarioId", source = "userDetail.id")
    @Mapping(target = "carritoId", source = "shoppingCart.id")
    OrderDTO orderAOrderDTO(Order Order);

    @Mapping(target = "userDetail.id", source = "detalleUsuarioId")
    @Mapping(target = "shoppingCart.id", source = "carritoId")
    @Mapping(target = "active", constant = "true")
    Order orderUpdateDTOAOrder(OrderUpdateDTO Order);

    @Mapping(target = "detalleUsuarioId", source = "userDetail.id")
    @Mapping(target = "carritoId", source = "shoppingCart.id")
    List<OrderDTO> listaOrderAListaOrderDTO(List<Order> lista);

    Order orderEntityAOrder(OrderEntity orderEntity);

    OrderEntity orderAOrderEntity(Order order);

    default Optional<Order> optionalOrderEntityAOptionalOrder(Optional<OrderEntity> optional){
        return optional.map(this::orderEntityAOrder);
    }

    List<Order> listaOrderEntityAListaOrder(List<OrderEntity> lista);
}
