package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ShoppingCart;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ShoppingCartRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.ShoppingCartMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ShoppingCartRepositoryAdapter implements ShoppingCartRepository {

    private final JpaShoppingCartRepository shoppingCartRepository;

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return ShoppingCartMapper.instancia.optionalShoppingCartEntityAOptionalShoppingCart(shoppingCartRepository.findById(id));
    }

    @Override
    public Optional<ShoppingCart> findByUserId(Long userId) {
        return ShoppingCartMapper.instancia.optionalShoppingCartEntityAOptionalShoppingCart(shoppingCartRepository.findByUserId(userId));
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return ShoppingCartMapper.instancia.shoppingCartEntityAShoppingCart(shoppingCartRepository.save(ShoppingCartMapper.instancia.shoppingCartAShoppingCartEntity(shoppingCart)));
    }

    @Override
    public List<ShoppingCart> findAllByActiveTrue() {
        return ShoppingCartMapper.instancia.listaShoppingCartEntityAListaShoppingCart(shoppingCartRepository.findAllByActiveTrue());
    }

    @Override
    public boolean existsShoppingCartEntityByUserId(Long id) {
        return shoppingCartRepository.existsShoppingCartEntityByUserId(id);
    }


    @Override
    public void updateShoppingCartSetActiveForId(boolean active, Long id) {
        shoppingCartRepository.updateShoppingCartEntitySetActiveForId(active, id);
    }
}
