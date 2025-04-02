package be.bstorm.demospringapi.api.models.security.dtos;

import be.bstorm.demospringapi.dl.entities.Stock;

public record StockDTO(
        Long id,
        int quantiteDisponible,
        Long produitId,
        String produitNom,
        Long userId,
        String userNom) {

    public static StockDTO fromStock(Stock stock) {
        return new StockDTO(
                stock.getId(),
                stock.getQuantiteDisponible(),
                stock.getProduit().getId(),
                stock.getProduit().getNom(),  // On ajoute le nom du produit directement
                stock.getUser().getId(),
                stock.getUser().getFirstName() // On ajoute le nom de l'utilisateur directement
        );
    }
}
