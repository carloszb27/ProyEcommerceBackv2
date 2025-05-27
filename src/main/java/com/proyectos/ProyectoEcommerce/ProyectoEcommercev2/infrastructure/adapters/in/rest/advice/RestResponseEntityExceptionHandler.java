package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.advice;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.*;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.ApiResponse;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Hidden
@RestControllerAdvice
public class RestResponseEntityExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    @ExceptionHandler({DataIntegrityViolationException.class, IllegalArgumentException.class, ValidationException.class, ConstraintViolationException.class})
    public ResponseEntity<?> handleDataIntegrity(RuntimeException e/*, WebRequest webRequest*/) {

        return CustomResponseBuilder.getInstance().crearResponse(e);
    }

    @ExceptionHandler({UserException.class, SupplierException.class, ProductException.class, OrderException.class, UserDetailException.class, ShoppingCartException.class})
    public ResponseEntity<?> handleOtherDataIntegrity(RuntimeException e) {

        return CustomResponseBuilder.getInstance().crearResponse(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, Object> contenido = new HashMap<>();

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .fecha(new Date())
                .status(HttpStatus.BAD_REQUEST)
                .mensaje(request.getDescription(false))
                .contenido(ex.getBindingResult().getFieldErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage))
                .build();

        return CustomResponseBuilder.getInstance().crearResponse(apiResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest webRequest) {

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .fecha(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .mensaje(webRequest.getDescription(false))
                .contenido(exception.getMessage())
                .build();

        return CustomResponseBuilder.getInstance().crearResponse(apiResponse);
    }
}
