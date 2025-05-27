package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.OrderService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.UserDetailService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.*;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Order;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.CartStatus;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.OrderStatus;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.OrderException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.ShoppingCartException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.OrderRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ProductRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final UserDetailService userDetailService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ShoppingCartRepository shoppingCartRepository,
                            @Qualifier("sql") ProductRepository productRepository,
                            UserDetailService userDetailService) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.userDetailService = userDetailService;
    }

    @Override
    public List<Order> listarOrders() {
        List<Order> lista = orderRepository.findAllByActiveTrue();
        return lista;
    }

    @Override
    public Order listarOrderPorId(Long id) throws OrderException {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new OrderException(OrderException.NotFoundException(id)));
            return order;
        } catch (OrderException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public Order registrarOrder(Order order) {

        ShoppingCart carrito = shoppingCartRepository.findById(order.getShoppingCart().getId())
                .orElseThrow(()-> new ShoppingCartException(ShoppingCartException.NotFoundException(order.getShoppingCart().getId())));

        order.setUserDetail(userDetailService.listarDetallePorUserActual().get());

        actualizarStock(carrito);

        order.setOrderStatus(OrderStatus.PENDIENTE);
        order.setFechaCompra(new Date());
        order.setFechaEntrega(this.calcularFechaEntrega());

        Order nuevoOrder = orderRepository.save(order);
        darDeBajaShoppingCart(carrito.getId());

        return nuevoOrder;
    }

    @Override
    public Order actualizarOrder(Order order) throws OrderException {
        try {
            Order OrderExiste = orderRepository.findById(order.getId())
                    .orElseThrow(() -> new OrderException(OrderException.NotFoundException(order.getId())));

            return orderRepository.save(order);

        } catch (OrderException e) {
            throw new OrderException(e.getMessage());
        }
    }

    @Override
    public String eliminarOrder(Long id) throws OrderException {
        Order Order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException(OrderException.NotFoundException(id)));
        orderRepository.updateOrderSetActiveForId(false, id);

        return "La orden de venta se ha eliminado correctamente";
    }

    @Override
    public Date calcularFechaEntrega() {
        // ZonaId por defecto
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Un LocalDate de aqui en 15 dias
        LocalDate localDate = LocalDate.now().plusDays(15);
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    @Override
    public void darDeBajaShoppingCart(Long carritoId) {
        ShoppingCart carrito = shoppingCartRepository.findById(carritoId).get();
        carrito.setEstado(CartStatus.PAGADO);
        carrito.setActive(false);
        shoppingCartRepository.save(carrito);
    }

    public void actualizarStock(ShoppingCart carrito){
        List<Item> items = carrito.getItems();

        List<Product> products = new ArrayList<>();

        items.forEach(item -> {

            Product product = item.getProduct();

            Batch batch = product.getBatch();

            batch.setStock(batch.getStock() - item.getCantidad());

            products.add(product);
        });

        productRepository.saveAll(products);
    }

}
