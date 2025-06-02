package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.RoleEnum;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.UserException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.RoleEntity;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JpaUserRepositoryTest {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Autowired
    JpaRoleRepository jpaRoleRepository;

//    @Autowired
//    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {

        Set<RoleEntity> roles = Set.of(
                RoleEntity.builder().roleEnum(RoleEnum.USER).build(),
                RoleEntity.builder().roleEnum(RoleEnum.EMPLOYEE).build(),
                RoleEntity.builder().roleEnum(RoleEnum.ADMIN).build()
        );

        jpaRoleRepository.saveAll(roles);

        RoleEntity roleUser = jpaRoleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow();

        List<UserEntity> listUsers = new ArrayList<>(List.of(
                UserEntity.builder()
                        .firstname("Luis")
                        .lastname("Perez")
                        .cellphone("+51987654321")
                        .fechaNacimiento(new Date())
                        .email("luis.perez@gmail.com")
                        .password("Dgfdjg$&_gd784")
                        .active(true)
                        .roles(Set.of(roleUser))
                        .build(),
                UserEntity.builder()
                        .firstname("Miguel")
                        .lastname("Lopez")
                        .cellphone("+51963852741")
                        .fechaNacimiento(new Date())
                        .email("miguel.lopez@gmail.com")
                        .password("Dgfdjg$&_gd784")
                        .active(true)
                        .roles(Set.of(roleUser))
                        .build()
        ));

        jpaUserRepository.saveAll(listUsers);
    }

    @Test
    void findAllByActiveTrue() {
        List<UserEntity> lista = jpaUserRepository.findAllByActiveTrue();
        assertEquals(2, lista.size());
    }

    @Test
    void findOneByEmail() {

        UserEntity user = jpaUserRepository.findOneByEmail("luis.perez@gmail.com").orElseThrow();

        assertEquals("luis.perez@gmail.com", user.getEmail());
    }

    @Test
    void saveUser() {

        RoleEntity roleUser = jpaRoleRepository.findByRoleEnum(RoleEnum.USER)
                .orElseThrow();

        UserEntity user = UserEntity.builder()
                .firstname("Maria")
                .lastname("Perez")
                .cellphone("+51986573241")
                .fechaNacimiento(new Date())
                .email("maria.perez@gmail.com")
                .password("Dgfdjg$&_gd784")
                .active(true)
                .roles(Set.of(roleUser))
                .build();

        UserEntity nuevoUser = jpaUserRepository.save(user);
        assertEquals(user.getEmail(), nuevoUser.getEmail());
        assertNotNull(nuevoUser);
    }

    @Test
    void updateUserSetActiveForId() {

        UserEntity user = jpaUserRepository.findOneByEmail("luis.perez@gmail.com").orElseThrow();

        jpaUserRepository.updateUserSetActiveForId(false, user.getId());

        UserEntity updatedUser = jpaUserRepository.findById(user.getId()).orElseThrow();

        assertFalse(updatedUser.isActive());
    }
}