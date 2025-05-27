package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.UserDetailService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.UserDetailException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.UserDetailRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.user.SessionUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {
    
    private final UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetail> listarUserDetails() {
        List<UserDetail> lista = userDetailRepository.findAll();
        return lista;
    }

    @Override
    public UserDetail listarUserDetailPorId(Long id) throws UserDetailException {
        try {
            UserDetail userDetail = userDetailRepository.findById(id)
                    .orElseThrow(() -> new UserDetailException(UserDetailException.NotFoundException(id)));

            return userDetail;
        } catch(UserDetailException e) {
            throw new UserDetailException(e.getMessage());
        }
    }

    @Override
    public UserDetail registrarUserDetail(UserDetail userDetail) {;

        validarUserDetail(userDetail);

        Optional<UserDetail> userDetailExiste = listarDetallePorUserActual();

        userDetailExiste.ifPresent(usuario ->
                userDetail.setId(usuario.getId()));

        User userActual = SessionUser.getUserAutenticado();

        userDetail.setUser(userActual);

        UserDetail nuevoUserDetail= userDetailRepository.save(userDetail);

        return nuevoUserDetail;
    }

    @Override
    public UserDetail actualizarUserDetail(Long id, UserDetail userDetail) throws UserDetailException {
        try {
            UserDetail detalleUsuarioExiste = userDetailRepository.findById(id)
                    .orElseThrow(() -> new UserDetailException(UserDetailException.NotFoundException(id)));

            UserDetail userDetailActualizado = userDetailRepository.save(userDetail);
            return userDetailActualizado;

        } catch (UserDetailException e) {
            throw new UserDetailException(e.getMessage());
        }
    }

    @Override
    public void validarUserDetail(UserDetail userDetail) {

    }

    @Override
    public Optional<UserDetail> listarDetallePorUserActual() {

        User userActual = SessionUser.getUserAutenticado();

        return userDetailRepository.findByUserId(userActual.getId());
    }


}
