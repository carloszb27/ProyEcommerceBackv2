package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.File;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileService {

    File store(MultipartFile file) throws IOException;
    File getFile(UUID id) throws FileNotFoundException;
    List<ResponseFile> getAllFiles();
}
