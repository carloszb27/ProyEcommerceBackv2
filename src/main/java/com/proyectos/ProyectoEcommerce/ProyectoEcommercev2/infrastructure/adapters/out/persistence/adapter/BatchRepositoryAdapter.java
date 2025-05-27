package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Batch;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.BatchRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.BatchMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BatchRepositoryAdapter implements BatchRepository {

    private final JpaBatchRepository batchRepository;

    @Override
    public Batch save(Batch batch) {
        return BatchMapper.instancia.batchEntityABatch(batchRepository.save(BatchMapper.instancia.batchABatchEntity(batch)));
    }

    @Override
    public List<Batch> findAll() {
        return BatchMapper.instancia.listaBatchEntityAListaBatch(batchRepository.findAll());
    }

    @Override
    public Optional<Batch> findById(Long id) {
        return BatchMapper.instancia.optionalBatchEntityAOptionalBatch(batchRepository.findById(id));
    }

}
