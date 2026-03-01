package com.controllers;
import com.dtos.AuthLoginRequest;
import com.dtos.AuthLoginResponse;
import com.dtos.AuthentificationDTO;
import com.security.JwtService;
import com.services.AuthentificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Contrôleur REST exposant les endpoints
 * liés à l'authentification.
 *
 * <p>
 * Permet la gestion CRUD des utilisateurs.
 * </p>
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentificationController {

    private final AuthentificationService service;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public AuthentificationDTO register(@RequestBody AuthentificationDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public AuthLoginResponse login(@RequestBody AuthLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getMdp())
        );

        String token = jwtService.generateToken(authentication.getName());
        return AuthLoginResponse.builder()
                .token(token)
                .pseudo(authentication.getName())
                .build();
    }

    @GetMapping("/{pseudo}")
    public AuthentificationDTO getByPseudo(@PathVariable String pseudo) {
        return service.getByPseudo(pseudo);
    }

    @GetMapping
    public List<AuthentificationDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{pseudo}")
    public void delete(@PathVariable String pseudo) {
        service.delete(pseudo);
    }

    @PostMapping("/logout")
    public Map<String, String> logout() {
        return Map.of("message", "Déconnecté côté client: supprimez le token JWT.");
    }
}
