package sn.isi.l3gl.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Point d'entrée de product-api.
 *
 * scanBasePackages = "sn.isi.l3gl" → Spring détecte automatiquement :
 *   - les @RestController dans sn.isi.l3gl.api.controller
 *   - le @Service     ProductService    (sn.isi.l3gl.core.service)
 *   - le @Configuration JpaConfig       (sn.isi.l3gl.core.config)
 *
 * JpaConfig (product-core) déclare déjà @EnableJpaRepositories
 * → NE PAS redéclarer ici pour éviter le conflit de bean.
 *
 * @EntityScan est nécessaire car les entités JPA sont dans
 * un package externe (product-core), hors du scan par défaut.
 */
@SpringBootApplication(scanBasePackages = "sn.isi.l3gl")
@EntityScan(basePackages = "sn.isi.l3gl.core.entity")
public class ProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }
}
