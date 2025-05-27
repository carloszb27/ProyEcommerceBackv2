package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.OrderService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Order.OrderUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.OrderMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Order resource")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> listadoOrders(){
        List<OrderDTO> listaDTO = OrderMapper.instancia.listaOrderAListaOrderDTO(orderService.listarOrders());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarOrderPorId(@PathVariable Long id){
        OrderDTO orderDTO = OrderMapper.instancia.orderAOrderDTO(orderService.listarOrderPorId(id));
        return CustomResponseBuilder.getInstance().crearResponse(orderDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarOrder(@Valid @RequestBody OrderCreateDTO order){
        OrderDTO orderDTO = OrderMapper.instancia.orderAOrderDTO(orderService.registrarOrder(OrderMapper.instancia.orderCreateDTOAOrder(order)));
        return CustomResponseBuilder.getInstance().crearResponse(orderDTO, true, orderDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarOrder(@Valid @RequestBody OrderUpdateDTO order){
        OrderDTO orderDTO = OrderMapper.instancia.orderAOrderDTO(orderService.actualizarOrder(OrderMapper.instancia.orderUpdateDTOAOrder(order)));
        return CustomResponseBuilder.getInstance().crearResponse(orderDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOrder(@PathVariable Long id){
        String mensaje = orderService.eliminarOrder(id);
        return CustomResponseBuilder.getInstance().crearResponse(mensaje);
    }
}
