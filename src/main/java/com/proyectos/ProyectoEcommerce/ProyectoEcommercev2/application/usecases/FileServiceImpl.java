package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.FileService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.File;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ResponseFile;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public File store(MultipartFile fileRec) throws IOException {

        String fileName = StringUtils.cleanPath(fileRec.getOriginalFilename());

        File file = File.builder()
                .name(fileName)
                .type(fileRec.getContentType())
                .data(fileRec.getBytes())
                .build();

        return fileRepository.save(file);
    }

    @Override
    public File getFile(UUID id) throws FileNotFoundException {

        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("No se encontro el archivo"));

        return file;
    }

    @Override
    public List<ResponseFile> getAllFiles() {

        List<ResponseFile> files = fileRepository.findAll().stream()
                .map(file -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/fileManager/files/")
                            .path(file.getId().toString())
                            .toUriString();
                    return ResponseFile.builder()
                            .name(file.getName())
                            .url(fileDownloadUri)
                            .type(file.getType())
                            .size(file.getData().length)
                            .build();
                } ).collect(Collectors.toList());

        return files;
    }
}
