package alvarompdev.newprojectapi.service;

import alvarompdev.newprojectapi.custom_exceptions.ProductNotFoundException;
import alvarompdev.newprojectapi.dto.OffProduct;
import alvarompdev.newprojectapi.dto.OffNutriments;
import alvarompdev.newprojectapi.entity.Product;
import alvarompdev.newprojectapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para gestionar productos
 *
 * - Primero busca el producto por su código de barras en la base de datos local
 * - Si no encuentra el producto, consulta la API externa de Open Food Facts
 * - Si encuentra el producto, pero le falta información, actualiza los datos
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
@Service
public class ProductService {

    private final ProductRepository repo; // Repositorio JPA para acceder a los productos en la base de datos
    private final ExternalFoodClient external; // Cliente para acceder a la API externa de Open Food Facts

    /**
     * Constructor del servicio que inyecta el repositorio y el cliente externo
     *
     * @param repo Repositorio JPA para productos
     * @param external Cliente para acceder a la API externa de Open Food Facts
     */
    public ProductService(ProductRepository repo, ExternalFoodClient external) {
        this.repo = repo;
        this.external = external;
    }

    /**
     * Obtiene un producto por su código de barras
     *
     * @param barcode Código de barras del producto a buscar
     * @return Product objeto que contiene la información del producto
     */
    @Transactional
    public Product getOrFetch(String barcode) {
        return repo.findByBarcode(barcode)
                .map(existing -> {
                    // Se verifica si necesitamos actualizar algunos de los datos que antes eran null pero que ahora sí que tenemos
                    // Si una de todas las variables es null, entonces ya esta variable será null por lo que se sobreescribirán todos los datos,
                    // actualizándose así de esta manera
                    boolean needsUpdate =
                            // Datos del producto
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

                            // Datos nutricionales del producto
                            existing.getEnergyKcal() == null ||
                            existing.getProtein() == null ||
                            existing.getFat() == null ||
                            existing.getCarbs() == null ||
                            existing.getSugars() == null ||
                            existing.getSaturatedFat() == null ||
                            existing.getFiber() == null ||
                            existing.getSalt() == null ||
                            existing.getSodium() == null;

                    if (needsUpdate) { // Si necesita actualizar alguno de los datos (algún dato era null pero ahora sí que tenemos datos para ese campo)
                        OffProduct off = external.fetchByBarcode(barcode); // Se consulta la API externa de Open Food Facts para obtener los datos del producto
                        if (off != null) { // Si existe el producto en la API externa
                            System.out.println("Actualizando datos del producto con código: " + barcode); // Debug

                            // Se actualizan los datos del producto con los datos obtenidos de la API externa
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

                            OffNutriments n = off.getNutriments(); // Se obtienen los datos nutricionales del producto
                            if (n != null) { // Si existen los datos nutricionales
                                // Se actualizan los datos nutricionales del producto con los datos obtenidos de la API externa
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

                            return repo.save(existing); // Se guarda el producto actualizado en la base de datos
                        }
                    }

                    return existing; // Si no necesita actualizar ningún dato, se devuelve el producto existente tal cual
                })
                .orElseGet(() -> { // Si no existe el producto en la base de datos, se consulta la API externa
                    OffProduct off = external.fetchByBarcode(barcode); // Se obtiene el producto de la API externa
                    if (off == null) { // Si no se encuentra el producto en la API externa, se lanza una excepción
                        throw new ProductNotFoundException(barcode); // Se lanza la excepción personalizada
                    }

                    Product p = new Product(); // Se crea un nuevo objeto Product para guardar los datos obtenidos de la API externa
                    // Se asignan los datos del producto obtenido de la API externa al nuevo objeto Product
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

                    OffNutriments n = off.getNutriments(); // Se obtienen los datos nutricionales del producto
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

                    return repo.save(p); // Se guarda el nuevo producto en la base de datos y se devuelve
                });
    }

}