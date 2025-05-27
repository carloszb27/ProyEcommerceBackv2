package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ShoppingCart;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository {

    Optional<ShoppingCart> findById(Long id);
    Optional<ShoppingCart> findByUserId(Long userId);
    ShoppingCart save(ShoppingCart shoppingCart);
    List<ShoppingCart> findAllByActiveTrue();
    boolean existsShoppingCartEntityByUserId(Long id);
    void updateShoppingCartSetActiveForId(boolean active, Long id);
}
