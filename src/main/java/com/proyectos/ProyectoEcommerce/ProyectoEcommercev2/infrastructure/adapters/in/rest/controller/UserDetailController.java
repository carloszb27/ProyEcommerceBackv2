package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.UserDetail;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.UserDetailService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail.UserDetailCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail.UserDetailDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.UserDetail.UserDetailUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.UserDetailMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userDetail")
@RequiredArgsConstructor
@Slf4j
@Tag(name="User detail resource")
public class UserDetailController {
    
    private final UserDetailService userDetailService;

    @GetMapping("")
    public ResponseEntity<?> listadoUserDetails(){
        log.info("GET: userDetails {}");
        List<UserDetailDTO> listaDTO = UserDetailMapper.instancia.listaUserDetailAListaUserDetailDTO(userDetailService.listarUserDetails());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUserDetailPorId(@PathVariable Long id){
        log.info("GET: userDetails {}", id);
        UserDetail userDetail = userDetailService.listarUserDetailPorId(id);
        UserDetailDTO userDetailDTO = UserDetailMapper.instancia.userDetailAUserDetailDTO(userDetail);
        return CustomResponseBuilder.getInstance().crearResponse(userDetailDTO);
    }

    @PostMapping("")
    public ResponseEntity<?> registrarUserDetail(@Valid @RequestBody UserDetailCreateDTO userDetail) {
        log.info("POST: userDetails {}", userDetail.dni());
        UserDetail userDetail1 = UserDetailMapper.instancia.userDetailCreateDTOAUserDetail(userDetail);

        UserDetail userDetail2 = userDetailService.registrarUserDetail(userDetail1);

        UserDetailDTO userDetailDTO = UserDetailMapper.instancia.userDetailAUserDetailDTO(userDetail2);

        return CustomResponseBuilder.getInstance().crearResponse(userDetailDTO, true, userDetailDTO.id());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUserDetail(@PathVariable Long id,
                                                  @Valid @RequestBody UserDetailUpdateDTO userDetail){
        log.info("PUT: userDetails {}", id);
        UserDetailDTO userDetailDTO = UserDetailMapper.instancia.userDetailAUserDetailDTO(userDetailService.actualizarUserDetail(id, UserDetailMapper.instancia.userDetailUpdateDTOAUserDetail(userDetail)));
        return CustomResponseBuilder.getInstance().crearResponse(userDetailDTO);
    }
}
