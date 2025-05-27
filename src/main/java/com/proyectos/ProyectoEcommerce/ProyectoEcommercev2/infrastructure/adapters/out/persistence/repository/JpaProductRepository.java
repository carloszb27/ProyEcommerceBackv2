package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByActiveTrue();

    @Query("select p from ProductEntity p where p.active = true")
    PageImpl<ProductEntity> findPagesAllByActiveTrue(Pageable pageable);

    //@Query("select p from ProductEntity p where p.nombre like :nombre% and p.active = true")
    List<ProductEntity> findAllByNombreStartingWithIgnoreCaseAndActiveTrue(Sort sort, String nombre);

    @Modifying
    @Transactional
    @Query("update ProductEntity p set p.active=:active where p.id=:id")
    void updateProductSetActiveForId(@Param("active") boolean active, @Param("id") Long id);

    //@Query("select p from ProductEntity p where p.precio between :precio1 and :precio2 and c.active = true")
    List<ProductEntity> findAllByPrecioBetweenAndActiveTrue(double precio1, double precio2);

    @Query("select p from ProductEntity p where p.batch.stock between :cantidad1 and :cantidad2 and p.active=true")
    List<ProductEntity> findAllByCantidadBetweenAndActiveTrue(int cantidad1, int cantidad2);

    @Query("select p from ProductEntity p where p.batch.stock <= :cantidad and p.active=true")
    List<ProductEntity> findAllByCantidadLessThanEqualAndActiveTrue(int cantidad);

    // Products que no vencen aun
    List<ProductEntity> findAllByFechaVenGreaterThanAndActiveTrue(Date fecha);

    //listarProductsVencidos
    List<ProductEntity> findAllByFechaVenLessThanEqualAndActiveTrue(Date fecha);

    @Query("select p from ProductEntity p where p.fechaVen between CURRENT_DATE() and :fechaLimite ")
    List<ProductEntity> findAllByFechaVenProximoAVencer(@Param("fechaLimite") Date fechaLimite);

    @Query("select p from ProductEntity p where category = ?1 and p.active=true")
    List<ProductEntity> findAllByCategory(Category category);

    @Query("select p from ProductEntity p where p.batch.id = :batchId")
    ProductEntity findProductByBatchId(@Param("batchId") Long batchId);

    @Query("select p from ProductEntity p join p.batch l where l.supplier.id = :supplier")
    List<ProductEntity> findAllBySupplierId(@Param("supplier") Long supplier);

    @Modifying
    @Transactional
    @Query("update ProductEntity p set p.precio = ROUND(p.precio * :factor, 4) where p.active = true")
    void updateAllProductsPrecios(@Param("factor") double factor);
}
