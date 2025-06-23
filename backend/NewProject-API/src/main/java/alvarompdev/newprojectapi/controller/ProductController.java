package alvarompdev.newprojectapi.controller;

import alvarompdev.newprojectapi.entity.Product;
import alvarompdev.newprojectapi.service.ProductNotFoundException;
import alvarompdev.newprojectapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService svc;

    public ProductController(ProductService svc) {
        this.svc = svc;
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<Product> getProduct(@PathVariable String barcode) {
        try {
            Product p = svc.getOrFetch(barcode);
            return ResponseEntity.ok(p);
        } catch (ProductNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}