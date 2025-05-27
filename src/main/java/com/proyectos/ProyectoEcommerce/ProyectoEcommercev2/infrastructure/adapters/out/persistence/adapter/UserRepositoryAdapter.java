package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.adapter;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.UserRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaUserDetailRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.UserMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository userRepository;

    private final JpaUserDetailRepository userDetailRepository;

    @Override
    public User save(User user) {
        return UserMapper.instancia.userEntityAUser(userRepository.save(UserMapper.instancia.userAUserEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        var optional = UserMapper.instancia.optionalUserEntityAOptionalUser(userRepository.findById(id));

        optional.ifPresent(user -> { userDetailRepository.findByUserId(id)
                .ifPresent(detail -> user.setDetalleUsuarioId(detail.getId()) );
        });

        return optional;

    }

    @Override
    public List<User> findAllByActiveTrue() {
        return UserMapper.instancia.listaUserEntityAListaUser(userRepository.findAllByActiveTrue());
    }

//    @Override
//    public User findByDni(String dni) {
//        return UserMapper.instancia.userEntityAUser(userRepository.findByDni(dni));
//    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return UserMapper.instancia.optionalUserEntityAOptionalUser(userRepository.findOneByEmail(email));
    }

//    @Override
//    public User findByDetalleUsuarioId(Long idDetalleUsuario) {
//        return UserMapper.instancia.userEntityAUser(userRepository.findByUserDetailId(idDetalleUsuario));
//    }

    @Override
    public void updateUserSetActiveForId(boolean active, Long id) {
        userRepository.updateUserSetActiveForId(active, id);
    }
}
