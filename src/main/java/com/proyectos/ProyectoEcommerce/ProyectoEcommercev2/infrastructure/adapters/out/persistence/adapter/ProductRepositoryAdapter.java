package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ProductRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.ProductMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("sql")
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return ProductMapper.instancia.optionalProductEntityAOptionalProduct(productRepository.findById(id));
    }

    @Override
    public List<Product> findAllByActiveTrue() {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByActiveTrue());
    }

    @Override
    public Page<Product> findPagesAllByActiveTrue(Pageable pageable) {
        return productRepository.findPagesAllByActiveTrue(pageable).map(ProductMapper.instancia::productEntityAProduct);
    }

    @Override
    public List<Product> findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort sort, String nombre) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByNombreStartingWithIgnoreCaseAndActiveTrue(sort, nombre));
    }

    @Override
    public void updateProductSetActiveForId(boolean active, Long id) {
        productRepository.updateProductSetActiveForId(active, id);
    }

    @Override
    public List<Product> findAllByPrecioBetweenAndActiveTrue(double precio1, double precio2) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByPrecioBetweenAndActiveTrue(precio1, precio2));
    }

    @Override
    public List<Product> findAllByCantidadBetweenAndActiveTrue(int cantidad1, int cantidad2) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByCantidadBetweenAndActiveTrue(cantidad1, cantidad2));
    }

    @Override
    public List<Product> findAllByCantidadLessThanEqualAndActiveTrue(int cantidad) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByCantidadLessThanEqualAndActiveTrue(cantidad));
    }

    @Override
    public List<Product> findAllByFechaVenGreaterThanAndActiveTrue(Date fecha) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByFechaVenGreaterThanAndActiveTrue(fecha));
    }

    @Override
    public List<Product> findAllByFechaVenLessThanEqualAndActiveTrue(Date fecha) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByFechaVenLessThanEqualAndActiveTrue(fecha));
    }

    @Override
    public List<Product> findAllByFechaVenProximoAVencer(Date fechaLimite) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByFechaVenGreaterThanAndActiveTrue(fechaLimite));
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllByCategory(category));
    }

    @Override
    public Product findProductByBatchId(Long batchId) {
        return ProductMapper.instancia.productEntityAProduct(productRepository.findProductByBatchId(batchId));
    }

    @Override
    public List<Product> findAllBySupplierId(Long supplier) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.findAllBySupplierId(supplier));
    }

    @Override
    public Product save(Product product) {
        return ProductMapper.instancia.productEntityAProduct(productRepository.save(ProductMapper.instancia.productAProductEntity(product)));
    }

    @Override
    public List<Product> saveAll(List<Product> lista) {
        return ProductMapper.instancia.listaProductEntityAListaProduct(productRepository.saveAll(ProductMapper.instancia.listaProductAListaProductEntity(lista)));
    }

    @Override
    public void updateAllProductsPrecios(double factor) {
        productRepository.updateAllProductsPrecios(factor);
    }
}
