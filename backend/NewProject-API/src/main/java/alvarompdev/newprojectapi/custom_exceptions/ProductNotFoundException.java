package alvarompdev.newprojectapi.custom_exceptions;

/**
 * Excepción personalizada para indicar que un producto no ha sido encontrado
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String barcode) {
        super("Producto no encontrado: " + barcode);
    }

}