package alvarompdev.newprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffProduct {
    @JsonProperty("product_name")
    private String productName;

    private String brands;

    @JsonProperty("image_front_url")
    private String imageUrl;

    @JsonProperty("ingredients_text")
    private String ingredients;

    private OffNutriments nutriments;

    @JsonProperty("nutriscore_grade")
    private String nutriscoreGrade;

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
}