package com.security;

import com.clients.UserDirectoryClient;
import com.clients.UserDirectoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Charge un utilisateur à partir du pseudo stocké en base.
 */
@Service
@RequiredArgsConstructor
public class AuthentificationUserDetailsService implements UserDetailsService {

    private final UserDirectoryClient userDirectoryClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDirectoryUser user = userDirectoryClient.fetchByPseudo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé (API utilisateur)"));

        return User.withUsername(user.pseudo())
                .password(user.mdp())
                .roles("USER")
                .build();
    }
}
