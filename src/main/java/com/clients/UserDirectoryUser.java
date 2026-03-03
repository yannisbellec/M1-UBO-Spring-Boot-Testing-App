package com.clients;

/**
 * Données minimales nécessaires à l'authentification.
 * Le mot de passe est déjà hashé côté API utilisateur.
 */
public record UserDirectoryUser(String pseudo, String mdp, String statut) {
}
