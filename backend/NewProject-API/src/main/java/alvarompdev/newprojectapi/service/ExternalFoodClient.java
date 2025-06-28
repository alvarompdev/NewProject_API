package alvarompdev.newprojectapi.service;

import alvarompdev.newprojectapi.dto.OffProduct;
import alvarompdev.newprojectapi.dto.OffProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Cliente encargado de consumir la API REST externa de Open Food Facts
 * Realiza peticiones HTTP para obtener información de productos alimenticios
 *
 * Usa un RestTemplate para hacer una llamada GET al endpoint de Open Food Facts
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
@Component
public class ExternalFoodClient {

    // URL base para acceder a la API de Open Food Facts con campos filtrados
    /*
    private static final String URL =
            "https://world.openfoodfacts.org/api/v0/product/{barcode}.json" +
                    "?fields=product_name,brands,image_front_url,nutriments,ingredients_text,nutriscore_grade,quantity,product_quantity,product_quantity_unit," +
                    "nova_group,nova_group_debug,ecoscore_score,ecoscore_grade";
     */
    // Si se quiere añadir algún nuevo campo que se quiere añadir a la petición, se tiene que añadir a esta URL también

    private static final String URL =
            "https://world.openfoodfacts.org/api/v0/product/{barcode}.json" +
                    "?fields=product_name,brands,image_front_url,nutriments,ingredients_text,nutriscore_grade,quantity,product_quantity,product_quantity_unit," +
                    "nova_group,nova_group_debug,ecoscore_score,ecoscore_grade,additives_tags,additives_original_tags,ingredients_analysis_tags";

    private final RestTemplate rest; // Cliente HTTP para realizar peticiones a la API externa

    /**
     * Constructor que inyecta el RestTemplate necesario para realizar las peticiones HTTP
     *
     * @param restTemplate Instancia de RestTemplate para realizar peticiones HTTP
     */
    public ExternalFoodClient(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    /**
     * Realiza una petición a la API de Open Food Facts para obtener información de un producto
     *
     * @param barcode Código de barras del producto a buscar
     * @return OffProduct objeto que contiene la información del producto si se encuentra,
     */
    public OffProduct fetchByBarcode(String barcode) {
        OffProductResponse resp = rest.getForObject(URL, OffProductResponse.class, barcode);
        if (resp != null && resp.getStatus() == 1) {
            return resp.getProduct();
        }
        return null;
    }

}