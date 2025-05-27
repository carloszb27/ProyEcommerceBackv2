package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> listarShoppingCarts();
    ShoppingCart listarShoppingCartPorId(Long id);
    ShoppingCart registrarShoppingCart(ShoppingCart shoppingCart);
    ShoppingCart actualizarShoppingCart(ShoppingCart shoppingCart);
    String eliminarShoppingCart(Long id);
    ShoppingCart listarShoppingCartPorUserActual();
}
