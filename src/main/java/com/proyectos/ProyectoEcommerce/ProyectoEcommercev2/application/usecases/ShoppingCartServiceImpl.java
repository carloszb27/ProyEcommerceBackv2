package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;


import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.ShoppingCartService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Item;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ShoppingCart;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.CartStatus;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.ProductException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.ShoppingCartException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.UserException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ProductRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ShoppingCartRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.user.SessionUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   @Qualifier("sql") ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ShoppingCart> listarShoppingCarts() {
        List<ShoppingCart> lista = shoppingCartRepository.findAllByActiveTrue();
        return lista;
    }

    @Override
    public ShoppingCart listarShoppingCartPorId(Long id) throws ShoppingCartException {

        try {
            ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                    .orElseThrow(() -> new ShoppingCartException(ShoppingCartException.NotFoundException(id)));
            return shoppingCart;

        } catch (ShoppingCartException e) {
            throw new ShoppingCartException(e.getMessage());
        }
    }

    @Override
    public ShoppingCart registrarShoppingCart(ShoppingCart shoppingCart) throws UserException, ProductException {

        try {

            User userActual = SessionUser.getUserAutenticado();
            Optional<ShoppingCart> shoppingCartExiste = shoppingCartRepository.findByUserId(userActual.getId());

            if(shoppingCartExiste.isPresent()) {
                shoppingCart.setId(shoppingCartExiste.get().getId());
                shoppingCart.setEstado(CartStatus.ACTUALIZADO);
            } else {
                shoppingCart.setEstado(CartStatus.CREADO);
            }

             shoppingCart.setUser(userActual);

            // Variable para asignar el precio total del ShoppingCart
            double precioTotal = 0.0;
            // Una lista de detalleShoppingCart que sera utilizada para guardar los valores
            List<Item> items = shoppingCart.getItems();
            for (Item item : items) {
                // Validar si los products existen
                Product product = productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new ProductException(ProductException.NotFoundException(item.getProduct().getId())));

                int cantidadItem = item.getCantidad();

                int stock = product.getBatch().getStock();

                if(cantidadItem <= 0) {
                    throw new ShoppingCartException("La cantidad no puede ser menor o igual a cero");
                }

                if(stock < 5 || stock - cantidadItem < 5 ) {
                    throw new ShoppingCartException("No hay suficiente stock");
                }

                item.setPrecio(Math.round(product.getPrecio()));
                precioTotal += item.getCantidad() * item.getPrecio();
            }
            shoppingCart.setPrecio(precioTotal);
            return shoppingCartRepository.save(shoppingCart);
        } catch (ShoppingCartException e) {
            throw new ShoppingCartException(e.getMessage());
        }
    }

    @Override
    public ShoppingCart actualizarShoppingCart(ShoppingCart shoppingCart) throws ShoppingCartException {
        try {
            ShoppingCart shoppingCartExiste = shoppingCartRepository.findById(shoppingCart.getId())
                    .orElseThrow(() -> new ShoppingCartException(ShoppingCartException.NotFoundException(shoppingCart.getId())));
            return shoppingCartRepository.save(shoppingCart);
        } catch (ShoppingCartException e) {
            throw new ShoppingCartException(e.getMessage());
        }
    }

    @Override
    public String eliminarShoppingCart(Long id) throws ShoppingCartException {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ShoppingCartException(ShoppingCartException.NotFoundException(id)));
        shoppingCartRepository.updateShoppingCartSetActiveForId(false, id);
        return "Se ha eliminado el shoppingCart correctamente";
    }

    @Override
    public ShoppingCart listarShoppingCartPorUserActual() {
        User userActual = SessionUser.getUserAutenticado();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userActual.getId())
                .orElse(new ShoppingCart());
        return shoppingCart;
    }

}
