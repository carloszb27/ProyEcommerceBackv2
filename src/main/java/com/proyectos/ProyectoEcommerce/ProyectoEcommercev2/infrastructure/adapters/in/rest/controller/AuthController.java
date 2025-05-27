package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.AuthService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Auth;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.AuthResponse;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.User.UserCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
//@SecurityRequirement(name = "Bearer Authentication")
@Tag(name="Auth resource")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserCreateDTO request){
        return ResponseEntity.ok(authService.register(UserMapper.instancia.userCreateDTOAUser(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody Auth request) throws IOException {
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
