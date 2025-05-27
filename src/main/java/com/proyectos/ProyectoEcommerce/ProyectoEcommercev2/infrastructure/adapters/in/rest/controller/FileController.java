package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.FileService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.File;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.ResponseFile;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fileManager")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        File newFile = fileService.store(file);
        //return ResponseEntity.ok(Map.of("message", "Archivo subido exitosamente"));
        return CustomResponseBuilder.getInstance().crearResponse(newFile, true, newFile.getId());
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) throws FileNotFoundException {

        File file = fileService.getFile(id);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, file.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ file.getName()+"\"")
                .body(file.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<?> getListFiles(){

        List<ResponseFile> lista = fileService.getAllFiles();

        return CustomResponseBuilder.getInstance().crearResponse(lista);
        //return ResponseEntity.ok(lista);
    }

}
