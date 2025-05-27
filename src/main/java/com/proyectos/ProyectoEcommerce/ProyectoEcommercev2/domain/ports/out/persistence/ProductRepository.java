package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {

    Optional<Product> findById(Long id);
    List<Product> findAllByActiveTrue();
    Page<Product> findPagesAllByActiveTrue(Pageable pageable);
    List<Product> findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort sort, String nombre);
    void updateProductSetActiveForId(boolean active, Long id);
    List<Product> findAllByPrecioBetweenAndActiveTrue(double precio1, double precio2);
    List<Product> findAllByCantidadBetweenAndActiveTrue(int cantidad1, int cantidad2);
    List<Product> findAllByCantidadLessThanEqualAndActiveTrue(int cantidad);
    List<Product> findAllByFechaVenGreaterThanAndActiveTrue(Date fecha);
    List<Product> findAllByFechaVenLessThanEqualAndActiveTrue(Date fecha);
    List<Product> findAllByFechaVenProximoAVencer(Date fechaLimite);
    List<Product> findAllByCategory(Category category);
    Product findProductByBatchId(Long batchId);
    List<Product> findAllBySupplierId(Long supplier);
    Product save(Product product);
    List<Product> saveAll(List<Product> lista);
    void updateAllProductsPrecios(double factor);
}
