package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.controller;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.ProductService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product.ProductCreateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product.ProductDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.dto.Product.ProductUpdateDTO;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.util.mapper.ProductMapper;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.infrastructure.adapters.in.rest.response.CustomResponseBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Product resource")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> listadoProducts(){
        log.info("GET: product {}");
        List<ProductDTO> lista = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProducts());
        return CustomResponseBuilder.getInstance().crearResponse(lista);
    }

    @GetMapping("/paginado")
    public ResponseEntity<?> listadoProducts(@PageableDefault(page = 0, size = 10, sort = "nombre") Pageable pageable){
        log.info("GET: product {}");
        Page<ProductDTO> paginaProductsDTO = productService.listarProductsPaginado(pageable).map(ProductMapper.instancia::productAProductDto);
        return CustomResponseBuilder.getInstance().crearResponse(paginaProductsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarProductPorId(@PathVariable Long id){
        log.info("GET: product {}");
        ProductDTO productDTO = ProductMapper.instancia.productAProductDto(productService.listarProductPorId(id));
        return CustomResponseBuilder.getInstance().crearResponse(productDTO);
    }

    @GetMapping("/empiezaPor/{nombre}")
    public ResponseEntity<?> listadoProductsQueEmpiecenPor(@PathVariable String nombre){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsQueEmpiecenPor(nombre));
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }


    @GetMapping("/masComprados")
    public ResponseEntity<?> listadoProductsMasComprados(){
        log.info("GET: product {}");
        List<ProductDTO> productDTOS = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsMasComprados());
        return CustomResponseBuilder.getInstance().crearResponse(productDTOS);
    }


    @PostMapping("")
    public ResponseEntity<?> registrarProduct(@Valid @RequestBody ProductCreateDTO product){
        log.info("POST: product {}", product.nombre());
        ProductDTO productDTO = ProductMapper.instancia.productAProductDto(productService.registrarProduct(ProductMapper.instancia.productCreateDtoAProduct(product)));
        return CustomResponseBuilder.getInstance().crearResponse(productDTO, true, productDTO.id());
    }

    @PutMapping("")
    public ResponseEntity<?> actualizarProduct(@Valid @RequestBody ProductUpdateDTO product){
        log.info("PUT: product {}", product.id());
        ProductDTO productDTO = ProductMapper.instancia.productAProductDto(productService.actualizarProduct(ProductMapper.instancia.productUpdateDtoAProduct(product)));
        return CustomResponseBuilder.getInstance().crearResponse(productDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProduct(@PathVariable Long id){
        log.info("DELETE: product {}", id);
        String mensaje = productService.eliminarProduct(id);
        return CustomResponseBuilder.getInstance().crearResponse(mensaje);
    }

    @GetMapping("/precio")
    public ResponseEntity<?> listarProductsPorPrecioEntre(@RequestParam double p1, @RequestParam double p2){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsPorPrecioEntre(p1, p2));
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> listarProductsPorCantidadEntre(@RequestParam int c1, @RequestParam int c2){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsPorCantidadEntre(c1, c2));
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/muyPocaCantidad")
    public ResponseEntity<?> listarProductsPorMuyPocaCantidad(){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsPorMuyPocaCantidad());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/todaviaNoVencen")
    public ResponseEntity<?> listarProductsQueTodaviaNoVencen(){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsQueTodaviaNoVencen());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/vencenEnMenosUnMes")
    public ResponseEntity<?> listarProductsVencenMenosDeUnMes(){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsVencenMenosDeUnMes());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/vencidos")
    public ResponseEntity<?> listarProductsVencidos(){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsVencidos());
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> listarProductsPorCategory(@PathVariable String category){
       log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsPorCategory(category));
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<?> listarProductsPorSupplier(@PathVariable Long id){
        log.info("GET: product {}");
        List<ProductDTO> listaDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.listarProductsPorSupplier(id));
        return CustomResponseBuilder.getInstance().crearResponse(listaDTO);
    }

    @GetMapping("/lote/{id}")
    public ResponseEntity<?> listarProductPorBatch(@PathVariable Long id){
        log.info("GET: productByBatch {}", id);
        ProductDTO productDTO = ProductMapper.instancia.productAProductDto(productService.listarProductPorBatch(id));
        return CustomResponseBuilder.getInstance().crearResponse(productDTO);
    }

    @PutMapping("/aumentarPrecioProds/{porcentaje}")
    public ResponseEntity<?> actualizarPrecioProducts(@PathVariable Double porcentaje){
        log.info("PUT: product {}", porcentaje);
        String mensaje = productService.actualizarPrecioProducts(porcentaje);
        return CustomResponseBuilder.getInstance().crearResponse(mensaje);
    }

    @PutMapping("/actualizarPreciosIniciales")
    public ResponseEntity<?> actualizarPreciosOriginal(){
        log.info("PUT: product {}");
        List<ProductDTO> listaProductDTO = ProductMapper.instancia.listaProductAListaProductDto(productService.actualizarProductsAPrecioOriginal());
        return CustomResponseBuilder.getInstance().crearResponse(listaProductDTO);
    }
}
