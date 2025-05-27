package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.user;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases.UserDetailImplement;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionUser {

    public static User getUserAutenticado() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        User user = ((UserDetailImplement) authentication.getPrincipal()).getUser();

        /*
        if(authentication.getPrincipal() instanceof UserDetailImplement userDetailImplement) {
            user = userDetailImplement.getUser();
            System.out.println(user);
        }
        */

        return user;
    }
}
