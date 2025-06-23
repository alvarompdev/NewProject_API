package alvarompdev.newprojectapi.service;

import alvarompdev.newprojectapi.dto.OffProductResponse;
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
                .orElseGet(() -> {
                    OffProductResponse.OffProduct off = external.fetchByBarcode(barcode);
                    if (off == null) {
                        throw new ProductNotFoundException(barcode);
                    }

                    Product p = new Product();
                    p.setBarcode(barcode);
                    p.setName(off.getProductName());
                    p.setBrand(off.getBrands());
                    p.setImageUrl(off.getImageUrl());
                    p.setIngredients(off.getIngredients());

                    OffProductResponse.OffProduct.OffNutriments n = off.getNutriments();
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