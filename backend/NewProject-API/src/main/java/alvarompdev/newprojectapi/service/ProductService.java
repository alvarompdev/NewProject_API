package alvarompdev.newprojectapi.service;

import alvarompdev.newprojectapi.custom_exceptions.ProductNotFoundException;
import alvarompdev.newprojectapi.dto.OffProduct;
import alvarompdev.newprojectapi.dto.OffNutriments;
import alvarompdev.newprojectapi.entity.Product;
import alvarompdev.newprojectapi.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar productos
 *
 * - Primero busca el producto por su código de barras en la base de datos local
 * - Si no encuentra el producto, consulta la API externa de Open Food Facts
 * - Si encuentra el producto, pero le falta información, actualiza los datos
 *
 * @author Álvaro Muñoz Panadero
 */
@Service
public class ProductService {

    private final ProductRepository repo;
    private final ExternalFoodClient external;
    private final ObjectMapper mapper;

    public ProductService(ProductRepository repo, ExternalFoodClient external, ObjectMapper mapper) {
        this.repo = repo;
        this.external = external;
        this.mapper = mapper;
    }

    /**
     * Obtiene un producto por su código de barras (entidad Product)
     */
    @Transactional
    public Product getOrFetch(String barcode) {
        return repo.findByBarcode(barcode)
                .map(existing -> {
                    boolean needsUpdate =
                            existing.getName() == null ||
                                    existing.getBrand() == null ||
                                    existing.getImageUrl() == null ||
                                    existing.getQuantity() == null ||
                                    existing.getIngredients() == null ||
                                    existing.getNutriScore() == null ||
                                    existing.getNovaGroup() == null ||
                                    existing.getNovaGroupDebug() == null ||
                                    existing.getEcoscoreScore() == null ||
                                    existing.getEcoscoreGrade() == null ||
                                    existing.getAdditivesTags() == null ||
                                    existing.getAdditivesOriginalTags() == null ||
                                    existing.getIngredientsAnalysisTags() == null ||

                                    existing.getEnergyKcal() == null ||
                                    existing.getProtein() == null ||
                                    existing.getFat() == null ||
                                    existing.getCarbs() == null ||
                                    existing.getSugars() == null ||
                                    existing.getSaturatedFat() == null ||
                                    existing.getFiber() == null ||
                                    existing.getSalt() == null ||
                                    existing.getSodium() == null;

                    if (needsUpdate) {
                        OffProduct off = external.fetchByBarcode(barcode);
                        if (off != null) {
                            if (existing.getName() == null) existing.setName(off.getProductName());
                            if (existing.getBrand() == null) existing.setBrand(off.getBrands());
                            if (existing.getImageUrl() == null) existing.setImageUrl(off.getImageUrl());
                            if (existing.getQuantity() == null) existing.setQuantity(off.getNormalizedQuantity());
                            if (existing.getIngredients() == null) existing.setIngredients(off.getIngredients());
                            if (existing.getNutriScore() == null) existing.setNutriScore(off.getNutriscoreGrade());
                            if (existing.getNovaGroup() == null) existing.setNovaGroup(off.getNovaGroup());
                            if (existing.getNovaGroupDebug() == null) existing.setNovaGroupDebug(off.getNovaGroupDebug());
                            if (existing.getEcoscoreScore() == null) existing.setEcoscoreScore(off.getEcoscoreScore());
                            if (existing.getEcoscoreGrade() == null) existing.setEcoscoreGrade(off.getEcoscoreGrade());

                            if (existing.getAdditivesTags() == null && off.getAdditivesTags() != null) {
                                try {
                                    existing.setAdditivesTags(mapper.writeValueAsString(off.getAdditivesTags()));
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            if (existing.getAdditivesOriginalTags() == null && off.getAdditivesOriginalTags() != null) {
                                try {
                                    existing.setAdditivesOriginalTags(mapper.writeValueAsString(off.getAdditivesOriginalTags()));
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            if (existing.getIngredientsAnalysisTags() == null && off.getIngredientsAnalysisTags() != null) {
                                try {
                                    existing.setIngredientsAnalysisTags(mapper.writeValueAsString(off.getIngredientsAnalysisTags()));
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            OffNutriments n = off.getNutriments();
                            if (n != null) {
                                if (existing.getEnergyKcal() == null) existing.setEnergyKcal(n.getEnergyKcalPer100g());
                                if (existing.getProtein() == null) existing.setProtein(n.getProteinsPer100g());
                                if (existing.getFat() == null) existing.setFat(n.getFatPer100g());
                                if (existing.getCarbs() == null) existing.setCarbs(n.getCarbsPer100g());
                                if (existing.getSugars() == null) existing.setSugars(n.getSugarsPer100g());
                                if (existing.getSaturatedFat() == null) existing.setSaturatedFat(n.getSaturatedFatPer100g());
                                if (existing.getFiber() == null) existing.setFiber(n.getFiberPer100g());
                                if (existing.getSalt() == null) existing.setSalt(n.getSaltPer100g());
                                if (existing.getSodium() == null) existing.setSodium(n.getSodiumPer100g());
                            }

                            return repo.save(existing);
                        }
                    }
                    return existing;
                })
                .orElseGet(() -> {
                    OffProduct off = external.fetchByBarcode(barcode);
                    if (off == null) {
                        throw new ProductNotFoundException(barcode);
                    }

                    Product p = new Product();
                    p.setBarcode(barcode);
                    p.setName(off.getProductName());
                    p.setBrand(off.getBrands());
                    p.setImageUrl(off.getImageUrl());
                    p.setQuantity(off.getNormalizedQuantity());
                    p.setIngredients(off.getIngredients());
                    p.setNutriScore(off.getNutriscoreGrade());
                    p.setNovaGroup(off.getNovaGroup());
                    p.setNovaGroupDebug(off.getNovaGroupDebug());
                    p.setEcoscoreScore(off.getEcoscoreScore());
                    p.setEcoscoreGrade(off.getEcoscoreGrade());

                    if (off.getAdditivesTags() != null) {
                        try {
                            p.setAdditivesTags(mapper.writeValueAsString(off.getAdditivesTags()));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (off.getAdditivesOriginalTags() != null) {
                        try {
                            p.setAdditivesOriginalTags(mapper.writeValueAsString(off.getAdditivesOriginalTags()));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if (off.getIngredientsAnalysisTags() != null) {
                        try {
                            p.setIngredientsAnalysisTags(mapper.writeValueAsString(off.getIngredientsAnalysisTags()));
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    OffNutriments n = off.getNutriments();
                    if (n != null) {
                        p.setEnergyKcal(n.getEnergyKcalPer100g());
                        p.setProtein(n.getProteinsPer100g());
                        p.setFat(n.getFatPer100g());
                        p.setCarbs(n.getCarbsPer100g());
                        p.setSugars(n.getSugarsPer100g());
                        p.setSaturatedFat(n.getSaturatedFatPer100g());
                        p.setFiber(n.getFiberPer100g());
                        p.setSalt(n.getSaltPer100g());
                        p.setSodium(n.getSodiumPer100g());
                    }

                    return repo.save(p);
                });
    }

    /**
     * Convierte la entidad Product a un DTO OffProduct, parseando los JSON en String a listas
     */
    public OffProduct convertToDto(Product product) {
        OffProduct dto = new OffProduct();

        // dto.setBarcode(product.getBarcode());
        dto.setProductName(product.getName());
        dto.setBrands(product.getBrand());
        dto.setImageUrl(product.getImageUrl());
        dto.setQuantity(product.getQuantity());
        dto.setIngredients(product.getIngredients());
        dto.setNutriscoreGrade(product.getNutriScore());
        dto.setNovaGroup(product.getNovaGroup());
        dto.setNovaGroupDebug(product.getNovaGroupDebug());
        dto.setEcoscoreScore(product.getEcoscoreScore());
        dto.setEcoscoreGrade(product.getEcoscoreGrade());

        try {
            if (product.getAdditivesTags() != null) {
                List<String> additivesTags = mapper.readValue(product.getAdditivesTags(), List.class);
                dto.setAdditivesTags(additivesTags);
            }
            if (product.getAdditivesOriginalTags() != null) {
                List<String> additivesOriginalTags = mapper.readValue(product.getAdditivesOriginalTags(), List.class);
                dto.setAdditivesOriginalTags(additivesOriginalTags);
            }
            if (product.getIngredientsAnalysisTags() != null) {
                List<String> ingredientsAnalysisTags = mapper.readValue(product.getIngredientsAnalysisTags(), List.class);
                dto.setIngredientsAnalysisTags(ingredientsAnalysisTags);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON tags", e);
        }

        OffNutriments nutriments = new OffNutriments();
        nutriments.setEnergyKcalPer100g(product.getEnergyKcal());
        nutriments.setProteinsPer100g(product.getProtein());
        nutriments.setFatPer100g(product.getFat());
        nutriments.setCarbsPer100g(product.getCarbs());
        nutriments.setSugarsPer100g(product.getSugars());
        nutriments.setSaturatedFatPer100g(product.getSaturatedFat());
        nutriments.setFiberPer100g(product.getFiber());
        nutriments.setSaltPer100g(product.getSalt());
        nutriments.setSodiumPer100g(product.getSodium());

        dto.setNutriments(nutriments);

        return dto;
    }

}