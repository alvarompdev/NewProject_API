package alvarompdev.newprojectapi.repository;

import alvarompdev.newprojectapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Product
 * Permite realizar operaciones CRUD sobre los productos almacenados en la base de datos
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByBarcode(String barcode);
}