package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Order;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ShoppingCart;

import java.util.Date;
import java.util.List;

public interface OrderService {

    List<Order> listarOrders();
    Order listarOrderPorId(Long id);
    Order registrarOrder(Order order);
    Order actualizarOrder(Order order);
    String eliminarOrder(Long id);
    Date calcularFechaEntrega();
    void darDeBajaShoppingCart(Long shoppingCartId);
    void actualizarStock(ShoppingCart shoppingCart);
}