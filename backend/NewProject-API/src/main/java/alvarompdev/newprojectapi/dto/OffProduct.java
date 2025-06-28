package alvarompdev.newprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.util.List;

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

    @JsonProperty("additives_tags")
    private List<String> additivesTags;

    @JsonProperty("additives_original_tags")
    private List<String> additivesOriginalTags;

    @JsonProperty("ingredients_analysis_tags")
    private List<String> ingredientsAnalysisTags;

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

    public List<String> getAdditivesTags() {
        return additivesTags;
    }

    public void setAdditivesTags(List<String> additivesTags) {
        this.additivesTags = additivesTags;
    }

    public List<String> getAdditivesOriginalTags() {
        return additivesOriginalTags;
    }

    public void setAdditivesOriginalTags(List<String> additivesOriginalTags) {
        this.additivesOriginalTags = additivesOriginalTags;
    }

    public List<String> getIngredientsAnalysisTags() {
        return ingredientsAnalysisTags;
    }

    public void setIngredientsAnalysisTags(List<String> ingredientsAnalysisTags) {
        this.ingredientsAnalysisTags = ingredientsAnalysisTags;
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