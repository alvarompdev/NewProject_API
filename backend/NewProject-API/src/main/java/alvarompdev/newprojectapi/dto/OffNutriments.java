package alvarompdev.newprojectapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OffNutriments {
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

    public Double getEnergyKcalPer100g() {
        return energyKcalPer100g;
    }

    public void setEnergyKcalPer100g(Double energyKcalPer100g) {
        this.energyKcalPer100g = energyKcalPer100g;
    }

    public Double getEnergyKjPer100g() {
        return energyKjPer100g;
    }

    public void setEnergyKjPer100g(Double energyKjPer100g) {
        this.energyKjPer100g = energyKjPer100g;
    }

    public Double getProteinsPer100g() {
        return proteinsPer100g;
    }

    public void setProteinsPer100g(Double proteinsPer100g) {
        this.proteinsPer100g = proteinsPer100g;
    }

    public Double getFatPer100g() {
        return fatPer100g;
    }

    public void setFatPer100g(Double fatPer100g) {
        this.fatPer100g = fatPer100g;
    }

    public Double getCarbsPer100g() {
        return carbsPer100g;
    }

    public void setCarbsPer100g(Double carbsPer100g) {
        this.carbsPer100g = carbsPer100g;
    }

    public Double getSugarsPer100g() {
        return sugarsPer100g;
    }

    public void setSugarsPer100g(Double sugarsPer100g) {
        this.sugarsPer100g = sugarsPer100g;
    }

    public Double getSaturatedFatPer100g() {
        return saturatedFatPer100g;
    }

    public void setSaturatedFatPer100g(Double saturatedFatPer100g) {
        this.saturatedFatPer100g = saturatedFatPer100g;
    }

    public Double getFiberPer100g() {
        return fiberPer100g;
    }

    public void setFiberPer100g(Double fiberPer100g) {
        this.fiberPer100g = fiberPer100g;
    }

    public Double getSaltPer100g() {
        return saltPer100g;
    }

    public void setSaltPer100g(Double saltPer100g) {
        this.saltPer100g = saltPer100g;
    }

    public Double getSodiumPer100g() {
        return sodiumPer100g;
    }

    public void setSodiumPer100g(Double sodiumPer100g) {
        this.sodiumPer100g = sodiumPer100g;
    }

}