package com.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDirectoryRestClient implements UserDirectoryClient {

    private final RestTemplate restTemplate;

    @Value("${user.api.base-url}")
    private String baseUrl;

    @Override
    public Optional<UserDirectoryUser> fetchByPseudo(String pseudo) {
        ResponseEntity<UserDirectoryUser[]> response =
                restTemplate.getForEntity(baseUrl + "/users", UserDirectoryUser[].class);
        List<UserDirectoryUser> users =
                Arrays.asList(Optional.ofNullable(response.getBody()).orElseGet(() -> new UserDirectoryUser[0]));

        return users.stream()
                .filter(u -> u.pseudo() != null && u.pseudo().equals(pseudo))
                .findFirst();
    }
}
