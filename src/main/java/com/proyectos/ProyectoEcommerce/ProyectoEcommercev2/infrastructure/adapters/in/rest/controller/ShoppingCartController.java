package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.ShoppingCartService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ShoppingCartCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ShoppingCartDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.ShoppingCart.ShoppingCartUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.ShoppingCartMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
@Slf4j
@Tag(name="ShoppingCart resource")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("")
    public ResponseEntity<?> listadoShoppingCarts(){
        List<ShoppingCartDTO> listaDTO = ShoppingCartMapper.instancia.listaShoppingCartAListaShoppingCartDTO(shoppingCartService.listarShoppingCarts());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarShoppingCartPorId(@PathVariable Long id){
        ShoppingCartDTO shoppingCartDTO = ShoppingCartMapper.instancia.shoppingCartAShoppingCartDTO(shoppingCartService.listarShoppingCartPorId(id));
        return CustomResponseBuilder.getInstance().crearResponse(shoppingCartDTO);
    }

    @GetMapping("/current")
    public ResponseEntity<?> listarShoppingCartCurrentUser(){
        ShoppingCartDTO shoppingCartDTO = ShoppingCartMapper.instancia.shoppingCartAShoppingCartDTO(shoppingCartService.listarShoppingCartPorUserActual());
        return CustomResponseBuilder.getInstance().crearResponse(shoppingCartDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarShoppingCart(@Valid @RequestBody ShoppingCartCreateDTO shoppingCart){
        ShoppingCartDTO shoppingCartDTO = ShoppingCartMapper.instancia.shoppingCartAShoppingCartDTO(shoppingCartService.registrarShoppingCart(ShoppingCartMapper.instancia.shoppingCartCreateDTOAShoppingCart(shoppingCart)));
        return CustomResponseBuilder.getInstance().crearResponse(shoppingCartDTO, true, shoppingCartDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarShoppingCart(@Valid @RequestBody ShoppingCartUpdateDTO shoppingCart){
        ShoppingCartDTO shoppingCartDTO = ShoppingCartMapper.instancia.shoppingCartAShoppingCartDTO(shoppingCartService.actualizarShoppingCart(ShoppingCartMapper.instancia.shoppingCartUpdateDTOAShoppingCart(shoppingCart)));
        return CustomResponseBuilder.getInstance().crearResponse(shoppingCartDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarShoppingCart(@PathVariable Long id){
        String mensaje = shoppingCartService.eliminarShoppingCart(id);
        return CustomResponseBuilder.getInstance().crearResponse(mensaje);
    }
}
