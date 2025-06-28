package alvarompdev.newprojectapi.dto;

/**
 * Representa la respuesta completa de la API de Open Food Facts
 * Contiene:
 * - El estado de la petición (status) que indica si el producto ha sido encontrado (1) o no (0)
 * - El objeto OffProduct que contiene la información del producto (si el produdto ha sido encontrado)
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
public class OffProductResponse {

    private int status; // Estado de la petición
    private OffProduct product; // Producto encontrado

    /**
     * Getters y Setters
     */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OffProduct getProduct() {
        return product;
    }

    public void setProduct(OffProduct product) {
        this.product = product;
    }

}