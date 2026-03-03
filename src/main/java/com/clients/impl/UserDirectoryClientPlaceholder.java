package com.clients.impl;

import com.clients.UserDirectoryClient;
import com.clients.UserDirectoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Placeholder: retourne vide tant que l'API utilisateur n'est pas branchée.
 */
@Component
@RequiredArgsConstructor
public class UserDirectoryClientPlaceholder implements UserDirectoryClient {

    @Value("${user.api.base-url:}")
    private String baseUrl;

    @Override
    public Optional<UserDirectoryUser> fetchByPseudo(String pseudo) {
        if (baseUrl == null || baseUrl.isBlank()) {
            return Optional.empty();
        }

        // TODO: brancher l'appel HTTP réel vers l'API utilisateur.
        return Optional.empty();
    }
}
