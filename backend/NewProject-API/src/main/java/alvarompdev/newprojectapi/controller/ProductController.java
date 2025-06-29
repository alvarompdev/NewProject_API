package alvarompdev.newprojectapi.controller;

import alvarompdev.newprojectapi.entity.Product;
import alvarompdev.newprojectapi.custom_exceptions.ProductNotFoundException;
import alvarompdev.newprojectapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que maneja las peticiones relacionadas con los productos
 * Permite obtener información de un producto a partir de su código de barras asociado
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService; // Servicio que maneja la lógica de negocio relacionada con los productos

    /**
     * Constructor del controlador que se encarga de inyectar el servicio de productos
     *
     * @param productService Servicio de productos
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Endpoint que obtiene toda la información de un producto a través de su código de barras
     * Primero busca el producto en la base de datos local y si no lo encuentra, lo busca en la API externa de Open Food Facts (esto lo hace el getOrFetch del servicio)
     *
     * HTTP GET: http://localhost:8080/api/product/4056489216162
     *
     * @param barcode Código de barras del producto a buscar
     * @return ResponseEntity con el producto encontrado o un error 404 si no se encuentra
     */
    @GetMapping("/{barcode}")
    public ResponseEntity<Product> getProduct(@PathVariable String barcode) {
        try {
            Product p = productService.getOrFetch(barcode);
            return ResponseEntity.ok(p);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}