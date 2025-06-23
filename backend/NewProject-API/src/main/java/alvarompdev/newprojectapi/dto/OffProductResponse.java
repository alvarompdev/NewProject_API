package alvarompdev.newprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffProductResponse {
    private int status;
    private OffProduct product;

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public OffProduct getProduct() { return product; }
    public void setProduct(OffProduct product) { this.product = product; }

    public static class OffProduct {
        @JsonProperty("product_name")
        private String productName;

        private String brands;

        @JsonProperty("image_front_url")
        private String imageUrl;

        @JsonProperty("ingredients_text")
        private String ingredients;

        private OffNutriments nutriments;

        public String getProductName() { return productName; }
        public void setProductName(String productName) { this.productName = productName; }

        public String getBrands() { return brands; }
        public void setBrands(String brands) { this.brands = brands; }

        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

        public String getIngredients() { return ingredients; }
        public void setIngredients(String ingredients) { this.ingredients = ingredients; }

        public OffNutriments getNutriments() { return nutriments; }
        public void setNutriments(OffNutriments nutriments) { this.nutriments = nutriments; }

        public static class OffNutriments {
            @JsonProperty("energy-kcal_100g")
            private Double energyKcalPer100g;

            @JsonProperty("energy-kj_100g")
            private Double energyKjPer100g;

            @JsonProperty("proteins_100g")
            private Double proteinsPer100g;

            @JsonProperty("fat_100g")
            private Double fatPer100g;

            @JsonProperty("carbohydrates_100g")
            private Double carbsPer100g;

            @JsonProperty("sugars_100g")
            private Double sugarsPer100g;

            @JsonProperty("saturated-fat_100g")
            private Double saturatedFatPer100g;

            @JsonProperty("fiber_100g")
            private Double fiberPer100g;

            @JsonProperty("salt_100g")
            private Double saltPer100g;

            @JsonProperty("sodium_100g")
            private Double sodiumPer100g;

            public Double getEnergyKcalPer100g() { return energyKcalPer100g; }
            public void setEnergyKcalPer100g(Double v) { this.energyKcalPer100g = v; }

            public Double getEnergyKjPer100g() { return energyKjPer100g; }
            public void setEnergyKjPer100g(Double v) { this.energyKjPer100g = v; }

            public Double getProteinsPer100g() { return proteinsPer100g; }
            public void setProteinsPer100g(Double v) { this.proteinsPer100g = v; }

            public Double getFatPer100g() { return fatPer100g; }
            public void setFatPer100g(Double v) { this.fatPer100g = v; }

            public Double getCarbsPer100g() { return carbsPer100g; }
            public void setCarbsPer100g(Double v) { this.carbsPer100g = v; }

            public Double getSugarsPer100g() { return sugarsPer100g; }
            public void setSugarsPer100g(Double v) { this.sugarsPer100g = v; }

            public Double getSaturatedFatPer100g() { return saturatedFatPer100g; }
            public void setSaturatedFatPer100g(Double v) { this.saturatedFatPer100g = v; }

            public Double getFiberPer100g() { return fiberPer100g; }
            public void setFiberPer100g(Double v) { this.fiberPer100g = v; }

            public Double getSaltPer100g() { return saltPer100g; }
            public void setSaltPer100g(Double v) { this.saltPer100g = v; }

            public Double getSodiumPer100g() { return sodiumPer100g; }
            public void setSodiumPer100g(Double v) { this.sodiumPer100g = v; }
        }
    }
}