package alvarompdev.newprojectapi.entity;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa un producto
 * Contiene todos los datos del producto, tanto los básicos como los nutricionales
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
@Entity
@Table(name = "products") // Nombre de la tabla en la base de datos
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Datos del producto
     */
    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(length = 500)
    private String name;

    @Column(length = 1000)
    private String brand;

    @Column(length = 1024)
    private String imageUrl;

    @Column(length = 10)
    private String quantity;

    @Column(length = 2000)
    private String ingredients;

    /**
     * Datos nutricionales del producto
     */
    private Double energyKcal;
    private Double protein;
    private Double fat;
    private Double carbs;
    private Double sugars;
    private Double saturatedFat;
    private Double fiber;
    private Double salt;
    private Double sodium;
    private String nutriScore;

    private Integer novaGroup;
    @Column(length = 255)
    private String novaGroupDebug;

    private Integer ecoscoreScore;
    @Column(length = 255)
    private String ecoscoreGrade;

    /*
     * Getters y Setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Double getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(Double energyKcal) {
        this.energyKcal = energyKcal;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(Double carbs) {
        this.carbs = carbs;
    }

    public Double getSugars() {
        return sugars;
    }

    public void setSugars(Double sugars) {
        this.sugars = sugars;
    }

    public Double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(Double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    public Double getSalt() {
        return salt;
    }

    public void setSalt(Double salt) {
        this.salt = salt;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public String getNutriScore() {
        return nutriScore;
    }

    public void setNutriScore(String nutriScore) {
        this.nutriScore = nutriScore;
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
}