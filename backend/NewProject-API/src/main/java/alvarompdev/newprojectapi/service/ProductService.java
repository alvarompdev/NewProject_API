package alvarompdev.newprojectapi.service;

import alvarompdev.newprojectapi.dto.OffProduct;
import alvarompdev.newprojectapi.dto.OffNutriments;
import alvarompdev.newprojectapi.entity.Product;
import alvarompdev.newprojectapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final ExternalFoodClient external;

    public ProductService(ProductRepository repo, ExternalFoodClient external) {
        this.repo = repo;
        this.external = external;
    }

    @Transactional
    public Product getOrFetch(String barcode) {
        return repo.findByBarcode(barcode)
                .map(existing -> {
                    // Si el producto ya existe pero nutriScore es null, actualizamos datos
                    if (existing.getNutriScore() == null) {
                        OffProduct off = external.fetchByBarcode(barcode);
                        if (off != null) {
                            System.out.println("Actualizando NutriScore para producto existente: " + off.getNutriscoreGrade());

                            existing.setNutriScore(off.getNutriscoreGrade());

                            // También puedes actualizar otros campos si quieres
                            if (existing.getName() == null) existing.setName(off.getProductName());
                            if (existing.getBrand() == null) existing.setBrand(off.getBrands());
                            if (existing.getImageUrl() == null) existing.setImageUrl(off.getImageUrl());
                            if (existing.getIngredients() == null) existing.setIngredients(off.getIngredients());

                            OffNutriments n = off.getNutriments();
                            if (n != null) {
                                existing.setEnergyKcal(n.getEnergyKcalPer100g());
                                existing.setProtein(n.getProteinsPer100g());
                                existing.setFat(n.getFatPer100g());
                                existing.setCarbs(n.getCarbsPer100g());
                                existing.setSugars(n.getSugarsPer100g());
                                existing.setSaturatedFat(n.getSaturatedFatPer100g());
                                existing.setFiber(n.getFiberPer100g());
                                existing.setSalt(n.getSaltPer100g());
                                existing.setSodium(n.getSodiumPer100g());
                            }

                            return repo.save(existing);
                        }
                    }
                    // Si el producto ya tiene nutriScore, devuelve el producto tal cual
                    return existing;
                })
                .orElseGet(() -> {
                    OffProduct off = external.fetchByBarcode(barcode);
                    if (off == null) {
                        throw new ProductNotFoundException(barcode);
                    }

                    System.out.println("NutriScore recibido: " + off.getNutriscoreGrade());

                    Product p = new Product();
                    p.setBarcode(barcode);
                    p.setName(off.getProductName());
                    p.setBrand(off.getBrands());
                    p.setImageUrl(off.getImageUrl());
                    p.setIngredients(off.getIngredients());
                    p.setNutriScore(off.getNutriscoreGrade());

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

}