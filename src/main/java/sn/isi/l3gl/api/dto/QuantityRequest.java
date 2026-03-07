package sn.isi.l3gl.api.dto;

/**
 * Corps de la requête PUT /api/products/{id}
 * Exemple JSON : { "quantity": 25 }
 */
public record QuantityRequest(Integer quantity) {}
