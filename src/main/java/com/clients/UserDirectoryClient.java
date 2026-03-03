package com.clients;

import java.util.Optional;

/**
 * Client vers l'API "utilisateur" (placeholder).
 * L'implémentation réelle sera branchée plus tard.
 */
public interface UserDirectoryClient {

    Optional<UserDirectoryUser> fetchByPseudo(String pseudo);
}
