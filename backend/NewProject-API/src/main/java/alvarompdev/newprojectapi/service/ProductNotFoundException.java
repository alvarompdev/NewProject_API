package alvarompdev.newprojectapi.service;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String barcode) {
        super("Producto no encontrado: " + barcode);
    }
}