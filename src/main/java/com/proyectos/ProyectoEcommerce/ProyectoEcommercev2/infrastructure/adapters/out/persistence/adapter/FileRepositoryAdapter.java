package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.File;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.FileRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.FileMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FileRepositoryAdapter implements FileRepository {

    private JpaFileRepository fileRepository;

    @Override
    public File save(File file) {
        return FileMapper.instancia.fileEntityAFile(FileMapper.instancia.fileAFileEntity(file));
    }

    @Override
    public Optional<File> findById(UUID id) {
        return FileMapper.instancia.optionalFileEntityAOptionalFile(fileRepository.findById(id));
    }

    @Override
    public List<File> findAll() {
        return FileMapper.instancia.listaFileEntityAListaFile(fileRepository.findAll());
    }
}
