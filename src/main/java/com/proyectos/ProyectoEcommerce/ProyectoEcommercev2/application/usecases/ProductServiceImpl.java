package com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.application.usecases;

import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.in.service.ProductService;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.model.Product;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.enums.Category;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.exception.ProductException;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.OrderRepository;
import com.proyectos.ProyectoEcommerce.ProyectoEcommercev2.domain.ports.out.persistence.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductRepository redisProductRepository;

    private final OrderRepository orderRepository;

    public ProductServiceImpl(@Qualifier("sql") ProductRepository productRepository,
                              @Qualifier("redis") ProductRepository redisProductRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.redisProductRepository = redisProductRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Product> listarProducts() {

        List<Product> productList = redisProductRepository.findAllByActiveTrue();

        if(!productList.isEmpty()) {
            log.info("Lista de productos encontrados en redis");
            return productList;
        }

        List<Product> lista = productRepository.findAllByActiveTrue();

        redisProductRepository.saveAll(lista);

        return lista;
    }

    @Override
    public Product listarProductPorId(Long id) throws ProductException {

        Optional<Product> optionalProduct = redisProductRepository.findById(id);

        if(optionalProduct.isPresent()) {
            log.info("Product encontrado en redis con id {}", id);
            return optionalProduct.get();
        }

        try {

            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductException(
                            ProductException.NotFoundException(id)));

            redisProductRepository.save(product);

            return product;

        } catch (ProductException e) {
            throw new ProductException(e.getMessage());
        }

    }

    @Override
    public List<Product> listarProductsMasComprados() {

        List<Product> products = orderRepository.findAllByActiveTrue()
                .stream()
                .flatMap(Order -> Order.getShoppingCart().getItems()
                         .stream()).map(items ->
                        items.getProduct()).collect(Collectors.toList());

        return products;

    }


    @Override
    public Product registrarProduct(Product product) {

        product.setPrecio(product.getBatch().getPrecio() * 1.3);
        return productRepository.save(product);

    }

    @Override
    public Product actualizarProduct(Product product) throws ProductException {

        try {
            Product productExiste = productRepository.findById(product.getId())
                    .orElseThrow(() -> new ProductException(ProductException
                            .NotFoundException(product.getId())));

            return productRepository.save(product);

        } catch (ProductException e) {
            throw new ProductException(e.getMessage());
        }

    }

    @Override
    public String eliminarProduct(Long id) throws ProductException {

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ProductException(ProductException.NotFoundException(id)));
            productRepository.updateProductSetActiveForId(false, id);

            return "El Product se ha eliminado correctamente";
        } catch (ProductException e) {
            throw new ProductException(e.getMessage());
        }

    }


    @Override
    public List<Product> actualizarProductsAPrecioOriginal() {

        List<Product> listaProducts = new ArrayList<>();
        productRepository.findAllByActiveTrue()
                .forEach(Product -> {

            double precioBatch = Product.getBatch().getPrecio();
            Product.setPrecio(precioBatch * 1.3);
            listaProducts.add(Product);
        });
        productRepository.saveAll(listaProducts);

       return productRepository.findAllByActiveTrue();
    }

    @Override
    public Page<Product> listarProductsPaginado(Pageable pageable) {
        Page<Product> paginaProducts = productRepository.findPagesAllByActiveTrue(pageable);
        return paginaProducts;
    }

    @Override
    public List<Product> listarProductsQueEmpiecenPor(String nombre) {
        List<Product> lista = productRepository
                .findAllByNombreStartingWithIgnoreCaseAndActiveTrue(
                Sort.by("nombre", "precio"),nombre);
        return lista;
    }

    @Override
    public List<Product> listarProductsPorPrecioEntre(double p1, double p2) {
        List<Product> lista = productRepository.findAllByPrecioBetweenAndActiveTrue(p1, p2);
        return lista;
    }

    @Override
    public List<Product> listarProductsPorCantidadEntre(int c1, int c2) {
        List<Product> lista = productRepository.findAllByCantidadBetweenAndActiveTrue(c1, c2);
        return lista;
    }

    @Override
    public List<Product> listarProductsPorMuyPocaCantidad() {
        List<Product> lista = productRepository.findAllByCantidadLessThanEqualAndActiveTrue(5);
        return lista;
    }

    @Override
    public List<Product> listarProductsQueTodaviaNoVencen() {
        List<Product> lista = productRepository
                .findAllByFechaVenGreaterThanAndActiveTrue(new Date());
        return lista;
    }

    @Override
    public List<Product> listarProductsVencenMenosDeUnMes() {

        List<Product> lista = productRepository
                .findAllByFechaVenProximoAVencer(this.calcularFechaProximoMes());
        return lista;
    }

    @Override
    public Date calcularFechaProximoMes() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        // Un LocalDate de aqui en un mes
        LocalDate localDate = LocalDate.now().plusMonths(1);
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    @Override
    public List<Product> listarProductsVencidos() {
        List<Product> lista = productRepository
                .findAllByFechaVenLessThanEqualAndActiveTrue(new Date());
        return lista;
    }

    @Override
    public List<Product> listarProductsPorCategory(String category) {
        List<Product> lista = productRepository.findAllByCategory(Category.valueOf(category.toUpperCase()));
        return lista;
    }

    @Override
    public List<Product> listarProductsPorSupplier(Long id) {
        List<Product> lista = productRepository.findAllBySupplierId(id);
        return lista;
    }

    @Override
    public Product listarProductPorBatch(Long id) {
        Product product = productRepository.findProductByBatchId(id);
        return product;
    }

    @Override
    public String actualizarPrecioProducts(Double porcentaje) {
        productRepository.updateAllProductsPrecios(1 + formatearDecimal(porcentaje));
        return "Se actualizo los Products en un " + porcentaje + " %";
    }

    @Override
    public double formatearDecimal(double numero) {
        BigDecimal bd = new BigDecimal(Double.toString(numero/100));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
