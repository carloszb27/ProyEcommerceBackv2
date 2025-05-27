package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.AuthService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Auth;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.AuthResponse;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Role;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.RoleRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.UserRepository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public AuthResponse register(User usuario) {
        log.info("Registrando un usuario");
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        Role role = roleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow(() -> new RuntimeException("Role no existe"));

        usuario.setRoles(Set.of(role));

        User nuevoUser = userRepository.save(usuario);
        String jwtToken = jwtService.generateToken(new UserDetailImplement(nuevoUser));
        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse authenticate(Auth request) throws IOException {
        log.info("Autenticando un usuario");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userRepository.findOneByEmail(request.getEmail())
                .orElseThrow();

        String jwToken = jwtService.generateToken(new UserDetailImplement(user));

        //LoginLogBuilder.getInstance().imprimirLog(user);

        return new AuthResponse(jwToken);
    }
}
