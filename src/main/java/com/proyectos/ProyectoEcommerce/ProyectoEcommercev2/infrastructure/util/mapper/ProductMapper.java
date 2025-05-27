package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product.ProductCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product.ProductDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product.ProductUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.ProductEntity;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.redis.entity.ProductCacheEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper instancia = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "batch.precio", source = "precioLote")
    @Mapping(target = "batch.stock", source = "stock")
    @Mapping(target = "batch.supplier.id", source = "proveedorId")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "descuento", constant = "0.0")
    Product productCreateDtoAProduct(ProductCreateDTO productCreateDTO);

    @Mapping(target = "precioLote", source = "batch.precio")
    @Mapping(target = "stock", source = "batch.stock")
    @Mapping(target = "proveedorId", source = "batch.supplier.id")
    ProductCreateDTO productAProductCreateDto(Product product);

    @Mapping(target = "stock", source = "batch.stock")
    ProductDTO productAProductDto(Product product);

    @Mapping(target = "batch.stock", source = "stock")
    @Mapping(target = "active", constant = "true")
    Product productDtoAProduct(ProductDTO productDTO);

    @Mapping(target = "batch.stock", source = "stock")
    @Mapping(target = "active", constant = "true")
    Product productUpdateDtoAProduct(ProductUpdateDTO productUpdateDTO);

    @Mapping(target = "stock", source = "batch.stock")
    List<ProductDTO> listaProductAListaProductDto (List<Product> listaProduct);

    @Mapping(target = "batch.stock", source = "stock")
    @Mapping(target = "active", constant = "true")
    List<Product> listaProductDtoAListaProduct (List<ProductDTO> listaProductDTO);

    Product productEntityAProduct(ProductEntity productEntity);

    ProductEntity productAProductEntity(Product product);

    default Optional<Product> optionalProductEntityAOptionalProduct(Optional<ProductEntity> optional){
        return optional.map(this::productEntityAProduct);
    }

    List<Product> listaProductEntityAListaProduct(List<ProductEntity> lista);

    List<ProductEntity> listaProductAListaProductEntity(List<Product> lista);

    // Redis

    Product productCacheEntityAProduct(ProductCacheEntity productCacheEntity);

    ProductCacheEntity productAProductCacheEntity(Product product);

    default Optional<Product> optionalProductCacheEntityAOptionalProduct(Optional<ProductCacheEntity> optional){
        return optional.map(this::productCacheEntityAProduct);
    }

    List<Product> listaProductCacheEntityAListaProduct(List<ProductCacheEntity> lista);

    List<ProductCacheEntity> listaProductAListaProductCacheEntity(List<Product> lista);



}
