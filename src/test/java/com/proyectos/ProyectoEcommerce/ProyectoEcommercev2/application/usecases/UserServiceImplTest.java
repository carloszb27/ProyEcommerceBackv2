package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Role;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.RoleRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.UserRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static List<User> listUsers;

    private static User user;

    private static User nuevoUser;

    private static User esperando;

    private static Role roleUser;

    @BeforeEach
    void setUp() {
    }

    @BeforeAll
    static void beforeAll() {

        user = User.builder()
                .id(1L)
                .firstname("Luis")
                .lastname("Perez")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .email("luis.perez@gmail.com")
                .password("Dgfdjg$&_gd784")
                .active(true)
                .roles(Set.of(Role.builder().roleEnum(RoleEnum.USER).build()))
                .build();

        esperando = User.builder()
                .id(1L)
                .firstname("Luis")
                .lastname("Perez")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .email("luis.perez@gmail.com")
                .password("Dgfdjg$&_gd784")
                .active(true)
                .roles(Set.of(Role.builder().roleEnum(RoleEnum.USER).build()))
                .build();

        nuevoUser = User.builder()
                .id(1L)
                .firstname("Luis")
                .lastname("Perez")
                .cellphone("+51987654321")
                .fechaNacimiento(new Date())
                .email("luis.perez@gmail.com")
                .password("Dgfdjg$&_gd784")
                .active(true)
                .roles(Set.of(Role.builder().roleEnum(RoleEnum.USER).build()))
                .build();

        listUsers = List.of(user,
                esperando,
               nuevoUser);

        roleUser = Role.builder().roleEnum(RoleEnum.USER).build();
    }

    @Test
    void listarUsers() {

        when(userRepository.findAllByActiveTrue())
                .thenReturn(listUsers);

        List<User> list = userService.listarUsers();

        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
        assertEquals("Luis", list.get(0).getFirstname());
        assertEquals(1L, list.get(1).getId());
        verify(userRepository).findAllByActiveTrue();
    }

    @Test
    void listarUserPorId() {

        Long id = 1L;

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(user));

        User resultado = userService.listarUserPorId(id);

        assertNotNull(resultado);
        assertEquals(esperando.getId(), resultado.getId());
        assertEquals(esperando.getEmail(), resultado.getEmail());

        verify(userRepository).findById(anyLong());
    }

    @Test
    void registrarUser() {

        when(roleRepository.findByRoleEnum(any(RoleEnum.class)))
                .thenReturn(Optional.of(roleUser));

        when(userRepository.save(any(User.class)))
                .thenReturn(nuevoUser);

        User resultado = userService.registrarUser(user);

        assertEquals(nuevoUser.getId(), resultado.getId());
        assertEquals(nuevoUser.getFirstname(), resultado.getFirstname());
        assertEquals(nuevoUser, resultado);
    }

    @Test
    void actualizarUser() {

        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(nuevoUser));
        when(userRepository.save(user)).thenReturn(nuevoUser);
        User actual = userService.actualizarUser(1L, user);
        assertEquals(user.getFirstname(), actual.getFirstname());
    }

    @Test
    void eliminarUser() {

        Long id = 1L;

        //When
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        String mensaje = userService.eliminarUser(id);

        //Then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository).updateUserSetActiveForId(eq(false), longArgumentCaptor.capture());

        assertEquals(1L, longArgumentCaptor.getValue());
        assertEquals("El user se ha eliminado correctamente", mensaje);
    }

    @Test
    void loadUserByUsername() {
    }
}