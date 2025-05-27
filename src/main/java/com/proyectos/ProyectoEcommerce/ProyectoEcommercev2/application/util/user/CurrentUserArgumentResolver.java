package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.user;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.util.aop.annotations.CurrentUser;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases.UserDetailImplement;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.User;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class)
                && User.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() ? ((UserDetailImplement)auth.getPrincipal()).getUser() : null;
    }
}
