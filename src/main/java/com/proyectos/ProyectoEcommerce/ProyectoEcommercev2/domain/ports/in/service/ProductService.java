package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ProductService {

    List<Product> listarProducts();
    Product listarProductPorId(Long id);
    List<Product> listarProductsMasComprados();
    Product registrarProduct(Product product);
    Product actualizarProduct(Product product);
    String eliminarProduct(Long id);
    List<Product> actualizarProductsAPrecioOriginal();
    Page<Product> listarProductsPaginado(Pageable pageable);
    List<Product> listarProductsQueEmpiecenPor(String nombre);
    List<Product> listarProductsPorPrecioEntre(double p1, double p2);
    List<Product> listarProductsPorCantidadEntre(int c1, int c2);
    List<Product> listarProductsPorMuyPocaCantidad();
    List<Product> listarProductsQueTodaviaNoVencen();
    List<Product> listarProductsVencenMenosDeUnMes();
    List<Product> listarProductsVencidos();
    List<Product> listarProductsPorCategory(String categoria);
    List<Product> listarProductsPorSupplier(Long id);
    Product listarProductPorBatch(Long id);
    String actualizarPrecioProducts(Double porcentaje);
    Date calcularFechaProximoMes();
    double formatearDecimal(double numero);
}