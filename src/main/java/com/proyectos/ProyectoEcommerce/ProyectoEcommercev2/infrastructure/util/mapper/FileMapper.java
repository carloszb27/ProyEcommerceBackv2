package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.File;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FileMapper {

    FileMapper instancia = Mappers.getMapper(FileMapper.class);

    File fileEntityAFile(FileEntity fileEntity);

    FileEntity fileAFileEntity(File file);

    default Optional<File> optionalFileEntityAOptionalFile(Optional<FileEntity> optional) {
        return optional.map(this::fileEntityAFile);
    };

    List<File> listaFileEntityAListaFile(List<FileEntity> lista);

}
