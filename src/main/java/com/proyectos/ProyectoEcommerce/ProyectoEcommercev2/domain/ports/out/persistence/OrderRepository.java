package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {

    Optional<Order> findById(Long id);
    Order save(Order order);
    List<Order> findAllByActiveTrue();
    List<Order> findAllByUser(Long idUser);
    void updateOrderSetActiveForId(boolean active, Long id);
}
