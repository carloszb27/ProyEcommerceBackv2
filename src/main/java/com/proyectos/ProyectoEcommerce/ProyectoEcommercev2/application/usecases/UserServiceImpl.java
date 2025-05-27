package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.UserService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Role;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.UserException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.RoleRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<User> listarUsers() {
        return userRepository.findAllByActiveTrue();
    }

    @Override
    public User listarUserPorId(Long id) throws UserException {
        try {
            return userRepository.findById(id)
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public User registrarUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()) );

        if(user.getRoles() == null || user.getRoles().isEmpty()) {
            Role roleUsuario = roleRepository.findByRoleEnum(RoleEnum.USER).get();
            user.setRoles(Set.of(roleUsuario));
            return userRepository.save(user);
        }

        Set<Role> roles = new HashSet<>();

        for(Role role : user.getRoles()) {

            RoleEnum roleEnum = role.getRoleEnum();

            Role roleExiste = roleRepository.findByRoleEnum(roleEnum)
                    .orElseThrow(() -> new RuntimeException("Role no existe"));

            roles.add(roleExiste);
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User actualizarUser(Long id, User user) throws UserException {
        try {
            User usuarioExiste = userRepository.findById(id)
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));
            if(user.getPassword() == null) {
                user.setPassword(usuarioExiste.getPassword());
            }
            return userRepository.save(user);
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }
    }

    @Override
    public String eliminarUser(Long id) throws UserException {
        try {
            User usuario = userRepository.findById(id)
                    .orElseThrow(() -> new UserException(UserException.NotFoundException(id)));
            userRepository.updateUserSetActiveForId(false, id);
            return "El user se ha eliminado correctamente";
        } catch (UserException e) {
            throw new UserException(e.getMessage());
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con dicho email: " + username + "no existe."));

        return new UserDetailImplement(user);
    }

}
