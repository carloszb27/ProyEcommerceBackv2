package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Batch;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.BatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BatchMapper {

    BatchMapper instancia = Mappers.getMapper(BatchMapper.class);

    Batch batchEntityABatch(BatchEntity batchEntity);

    BatchEntity batchABatchEntity(Batch batch);

    default Optional<Batch> optionalBatchEntityAOptionalBatch(Optional<BatchEntity> optional) {
        return optional.map(this::batchEntityABatch);
    };

    List<Batch> listaBatchEntityAListaBatch(List<BatchEntity> lista);

}
