package sn.isi.l3gl.api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.api.dto.QuantityRequest;
import sn.isi.l3gl.core.entity.Product;
import sn.isi.l3gl.core.service.ProductService;

/**
 * Contrôleur REST – couche API uniquement.
 *
 * Aucune logique métier ici : toutes les opérations
 * sont déléguées à ProductService (product-core).
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ─────────────────────────────────────────────────────────
    // POST /api/products
    // ─────────────────────────────────────────────────────────

    /**
     * Ajouter un nouveau produit.
     *
     * @param product corps JSON du produit à créer
     * @return 201 Created + produit persisté
     *
     * Exemple :
     * POST http://localhost:8086/api/products
     * {
     *   "name": "Laptop",
     *   "description": "PC 15 pouces",
     *   "price": 850.0,
     *   "quantity": 10
     * }
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ─────────────────────────────────────────────────────────
    // GET /api/products
    // ─────────────────────────────────────────────────────────

    /**
     * Lister tous les produits.
     *
     * @return 200 OK + liste des produits
     *
     * Exemple :
     * GET http://localhost:8086/api/products
     */
    @GetMapping
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }

    // ─────────────────────────────────────────────────────────
    // PUT /api/products/{id}
    // ─────────────────────────────────────────────────────────

    /**
     * Modifier la quantité en stock d'un produit.
     *
     * @param id      identifiant du produit
     * @param request corps JSON : { "quantity": 25 }
     * @return 200 OK + produit mis à jour
     *
     * Exemple :
     * PUT http://localhost:8086/api/products/1
     * { "quantity": 25 }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateQuantity(@PathVariable Long id, @RequestBody QuantityRequest request) {
        Product updated = productService.updateQuantity(id, request.quantity());
        return ResponseEntity.ok(updated);
    }

    // ─────────────────────────────────────────────────────────
    // GET /api/products/low-stock/count
    // ─────────────────────────────────────────────────────────

    /**
     * Retourner le nombre de produits en stock faible (quantity <= 5).
     *
     * @return 200 OK + nombre (long)
     *
     * Exemple :
     * GET http://localhost:8086/api/products/low-stock/count
     */
    @GetMapping("/low-stock/count")
    public ResponseEntity<Long> countLowStockProducts() {
        return ResponseEntity.ok(productService.countLowStockProducts());
    }
}
