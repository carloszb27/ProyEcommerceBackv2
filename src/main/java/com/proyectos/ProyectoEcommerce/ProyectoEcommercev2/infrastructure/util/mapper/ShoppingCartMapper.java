package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Item;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ShoppingCart;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ItemDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ShoppingCartCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ShoppingCartDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ShoppingCartUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.ItemEntity;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.ShoppingCartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING/*, uses = {ShoppingCartItemMapper.class}*/)
public interface ShoppingCartMapper {

    ShoppingCartMapper instancia = Mappers.getMapper(ShoppingCartMapper.class);

    @Mapping(target = "items", source = "carritoItems")
    @Mapping(target = "active", constant = "true")
    ShoppingCart shoppingCartCreateDTOAShoppingCart(ShoppingCartCreateDTO ShoppingCart);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "items", source = "carritoItems")
    @Mapping(target = "active", constant = "true")
    ShoppingCart shoppingCartUpdateDTOAShoppingCart(ShoppingCartUpdateDTO ShoppingCart);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "carritoItems", source = "items")
    ShoppingCartDTO shoppingCartAShoppingCartDTO(ShoppingCart ShoppingCart);

    @Mapping(target = "userId", source = "user.id")
    List<ShoppingCartDTO> listaShoppingCartAListaShoppingCartDTO(List<ShoppingCart> lista);

    ShoppingCart shoppingCartEntityAShoppingCart(ShoppingCartEntity shoppingCartEntity);

    ShoppingCartEntity shoppingCartAShoppingCartEntity(ShoppingCart shoppingCart);

    default Optional<ShoppingCart> optionalShoppingCartEntityAOptionalShoppingCart(Optional<ShoppingCartEntity> optional){
        return optional.map(this::shoppingCartEntityAShoppingCart);
    };

    List<ShoppingCart> listaShoppingCartEntityAListaShoppingCart(List<ShoppingCartEntity> lista);




    @Mapping(target = "product.id", source = "productoId")
    @Mapping(target = "active", constant = "true")
    Item shoppingCartItemDTOAShoppingCartItem(ItemDTO ShoppingCartItemDTO);

    @Mapping(target = "productoId", source = "product.id")
    ItemDTO shoppingCartItemAShoppingCartItemDTO(Item ShoppingCartItem);

    @Mapping(target = "product.id", source = "productoId")
    @Mapping(target = "active", constant = "true")
    List<Item> listaShoppingCartItemDTOAListaShoppingCartItem(List<ItemDTO> lista);

    @Mapping(target = "product.id", source = "productoId")
    List<ItemDTO> listaShoppingCartItemAListaShoppingCartItemDTO(List<Item> lista);

    Item itemEntityAItem(ItemEntity itemEntity);

    ItemEntity itemAItemEntity(Item item);

    default Optional<Item> optionalItemEntityAOptionalItem(Optional<ItemEntity> optional){
        return optional.map(this::itemEntityAItem);
    }

    List<Item> listaItemEntityAListaItem(List<ItemEntity> lista);

}
