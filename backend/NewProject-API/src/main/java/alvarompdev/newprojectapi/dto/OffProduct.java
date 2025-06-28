package alvarompdev.newprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa la información principal del producto recibida desde la API de Open Food Facts
 *
 * Se utiliza para mapear la respuesta JSON a variables Java. Por ejemplo:
 * El JSON de Open Food Facts tiene la propiedad 'product_name', que se asigna aquí
 * con @JsonProperty("product_name") a la variable 'productName'
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
public class OffProduct {

    @JsonProperty("product_name")
    private String productName;

    private String brands;

    @JsonProperty("image_front_url")
    private String imageUrl;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("product_quantity")
    private String productQuantity;

    @JsonProperty("product_quantity_unit")
    private String productQuantityUnit;

    @JsonProperty("ingredients_text")
    private String ingredients;

    private OffNutriments nutriments;

    @JsonProperty("nutriscore_grade")
    private String nutriscoreGrade;

    @JsonProperty("nova_group")
    private Integer novaGroup;

    @JsonProperty("nova_group_debug")
    private String novaGroupDebug;

    @JsonProperty("ecoscore_score")
    private Integer ecoscoreScore;

    @JsonProperty("ecoscore_grade")
    private String ecoscoreGrade;

    /**
     * Getters y Setters
     */
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductQuantityUnit() {
        return productQuantityUnit;
    }

    public void setProductQuantityUnit(String productQuantityUnit) {
        this.productQuantityUnit = productQuantityUnit;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public OffNutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(OffNutriments nutriments) {
        this.nutriments = nutriments;
    }

    public String getNutriscoreGrade() {
        return nutriscoreGrade;
    }

    public void setNutriscoreGrade(String nutriscoreGrade) {
        this.nutriscoreGrade = nutriscoreGrade;
    }

    public Integer getNovaGroup() {
        return novaGroup;
    }

    public void setNovaGroup(Integer novaGroup) {
        this.novaGroup = novaGroup;
    }

    public String getNovaGroupDebug() {
        return novaGroupDebug;
    }

    public void setNovaGroupDebug(String novaGroupDebug) {
        this.novaGroupDebug = novaGroupDebug;
    }

    public Integer getEcoscoreScore() {
        return ecoscoreScore;
    }

    public void setEcoscoreScore(Integer ecoscoreScore) {
        this.ecoscoreScore = ecoscoreScore;
    }

    public String getEcoscoreGrade() {
        return ecoscoreGrade;
    }

    public void setEcoscoreGrade(String ecoscoreGrade) {
        this.ecoscoreGrade = ecoscoreGrade;
    }

    public String getNormalizedQuantity() {
        if (quantity != null && !quantity.isEmpty()) {
            return quantity.trim();
        } else if (productQuantity != null && !productQuantity.isEmpty()) {
            String unit = productQuantityUnit != null ? productQuantityUnit : "";
            return productQuantity.trim() + " " + unit;
        } else {
            return null;
        }
    }

}