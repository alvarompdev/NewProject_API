package alvarompdev.newprojectapi.service;

import alvarompdev.newprojectapi.dto.OffProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalFoodClient {

    private static final String URL =
            "https://world.openfoodfacts.org/api/v0/product/{barcode}.json" +
                    "?fields=product_name,brands,image_front_url,nutriments,ingredients_text";

    private final RestTemplate rest;

    public ExternalFoodClient(RestTemplate restTemplate) {
        this.rest = restTemplate;
    }

    public OffProductResponse.OffProduct fetchByBarcode(String barcode) {
        OffProductResponse resp =
                rest.getForObject(URL, OffProductResponse.class, barcode);
        if (resp != null && resp.getStatus() == 1) {
            return resp.getProduct();
        }
        return null;
    }
}