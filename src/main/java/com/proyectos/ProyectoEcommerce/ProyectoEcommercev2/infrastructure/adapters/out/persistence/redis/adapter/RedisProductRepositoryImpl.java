package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.redis.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ProductRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.redis.entity.ProductCacheEntity;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.redis.repository.CacheProductRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("redis")
@RequiredArgsConstructor
public class RedisProductRepositoryImpl implements ProductRepository {

    private final CacheProductRepository cacheProductRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return cacheProductRepository.findById(id)
                .map(ProductMapper.instancia::productCacheEntityAProduct);
    }

    @Override
    public List<Product> findAllByActiveTrue() {
        return cacheProductRepository.findAll().stream()
                .map(ProductMapper.instancia::productCacheEntityAProduct).toList();
    }

    @Override
    public Page<Product> findPagesAllByActiveTrue(Pageable pageable) {
        return null;
    }

    @Override
    public List<Product> findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort sort, String nombre) {
        return List.of();
    }

    @Override
    public void updateProductSetActiveForId(boolean active, Long id) {

    }

    @Override
    public List<Product> findAllByPrecioBetweenAndActiveTrue(double precio1, double precio2) {
        return List.of();
    }

    @Override
    public List<Product> findAllByCantidadBetweenAndActiveTrue(int cantidad1, int cantidad2) {
        return List.of();
    }

    @Override
    public List<Product> findAllByCantidadLessThanEqualAndActiveTrue(int cantidad) {
        return List.of();
    }

    @Override
    public List<Product> findAllByFechaVenGreaterThanAndActiveTrue(Date fecha) {
        return List.of();
    }

    @Override
    public List<Product> findAllByFechaVenLessThanEqualAndActiveTrue(Date fecha) {
        return List.of();
    }

    @Override
    public List<Product> findAllByFechaVenProximoAVencer(Date fechaLimite) {
        return List.of();
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return List.of();
    }

    @Override
    public Product findProductByBatchId(Long batchId) {
        return null;
    }

    @Override
    public List<Product> findAllBySupplierId(Long supplier) {
        return List.of();
    }

    @Override
    public Product save(Product product) {
        ProductCacheEntity saved = cacheProductRepository.save(ProductMapper.instancia.
                productAProductCacheEntity(product));
        return ProductMapper.instancia.productCacheEntityAProduct(saved);
    }

    @Override
    public List<Product> saveAll(List<Product> lista) {
        return ProductMapper.instancia.listaProductCacheEntityAListaProduct(cacheProductRepository.saveAll(ProductMapper.instancia.listaProductAListaProductCacheEntity(lista)));
    }

    @Override
    public void updateAllProductsPrecios(double factor) {

    }
}
