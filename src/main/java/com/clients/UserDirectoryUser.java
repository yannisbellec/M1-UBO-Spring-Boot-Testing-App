package com.clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Données minimales nécessaires à l'authentification.
 * Le mot de passe est en clair côté API utilisateur.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDirectoryUser(
        @JsonProperty("pseudo") String pseudo,
        @JsonProperty("motDePasse") String mdp,
        @JsonProperty("statut") boolean statut
) {
}
