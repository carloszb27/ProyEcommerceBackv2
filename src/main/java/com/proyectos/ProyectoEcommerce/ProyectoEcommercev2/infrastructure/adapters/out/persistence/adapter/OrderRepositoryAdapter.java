package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Order;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.OrderRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.OrderMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository orderRepository;

    @Override
    public Optional<Order> findById(Long id) {
        return OrderMapper.instancia.optionalOrderEntityAOptionalOrder(orderRepository.findById(id));
    }

    @Override
    public Order save(Order order) {
        return OrderMapper.instancia.orderEntityAOrder(orderRepository.save(OrderMapper.instancia.orderAOrderEntity(order)));
    }

    @Override
    public List<Order> findAllByActiveTrue() {
        return OrderMapper.instancia.listaOrderEntityAListaOrder(orderRepository.findAllByActiveTrue());
    }

    @Override
    public List<Order> findAllByUser(Long idUser) {
        return OrderMapper.instancia.listaOrderEntityAListaOrder(orderRepository.findAllByUser(idUser));
    }

    @Override
    public void updateOrderSetActiveForId(boolean active, Long id) {
        orderRepository.updateOrdenVentaSetActiveForId(active, id);
    }
}
