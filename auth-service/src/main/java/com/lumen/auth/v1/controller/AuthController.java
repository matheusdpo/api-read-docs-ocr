package com.lumen.auth.v1.controller;

import com.lumen.auth.v1.entities.ApiKeyEntity;
import com.lumen.auth.v1.entities.UserEntity;
import com.lumen.auth.v1.repositories.ApiKeyRepository;
import com.lumen.auth.v1.repositories.UserRepository;
import com.lumen.auth.v1.utils.JwtUtils;
import com.lumen.auth.v1.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LogUtils logger;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity) {
        // Verificar se o usuário já existe
        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            return ResponseEntity.status(400).body("Usuário já existe!");
        }

        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Email já cadastrado!");
        }

        if (userRepository.findByPhone(userEntity.getPhone()).isPresent()) {
            return ResponseEntity.status(400).body("Telefone já cadastrado!");
        }

        // Codificar a senha antes de salvar
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);

        // Gera um token JWT para o novo usuário
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);

        // Retorna o token JWT na resposta
        return ResponseEntity.ok(jwt);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity) {
        try {
            logger.info("Usuário " + userEntity.getUsername() + " tentando fazer login...");
            // Autentica o usuário
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));

            // Gera o token JWT
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());
            final String jwt = jwtUtils.generateToken(userDetails);

            logger.info("Usuário " + userEntity.getUsername() + " fez login com sucesso!");
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            logger.error("Falha na autenticação: " + e.getMessage());
            return ResponseEntity.status(401).body("Falha na autenticação: " + e.getMessage());
        }
    }

    @PostMapping("/api-key")
    public ResponseEntity<String> createApiKey(@RequestHeader("Authorization") String token) {
        // Verifica se o token está presente e começa com "Bearer "
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Token inválido ou ausente");
        }

        // Remove o prefixo "Bearer " para obter o token JWT
        String jwtToken = token.substring(7);

        // Valida o token JWT
        if (!jwtUtils.validateToken(jwtToken, userDetailsService.loadUserByUsername(jwtUtils.extractUsername(jwtToken)))) {
            return ResponseEntity.status(400).body("Token JWT inválido");
        }

        // Extrai o nome de usuário do token
        String username = jwtUtils.extractUsername(jwtToken);

        // Busca o usuário no banco de dados
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Gera uma nova chave de API
        String apiKey = UUID.randomUUID().toString();

        // Cria e salva a entidade ApiKeyEntity
        ApiKeyEntity apiKeyEntity = new ApiKeyEntity(apiKey, userEntity);
        apiKeyRepository.save(apiKeyEntity);

        // Retorna a chave de API gerada
        return ResponseEntity.ok(apiKey);
    }

    @DeleteMapping("/api-key/{key}")
    public ResponseEntity<String> deleteApiKey(@PathVariable String key) {
        apiKeyRepository.deleteById(key);
        return ResponseEntity.ok("API Key removida com sucesso!");
    }

    @GetMapping("/api-keys")
    public ResponseEntity<List<ApiKeyEntity>> getApiKeys(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token.substring(7));
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        return ResponseEntity.ok(apiKeyRepository.findByUserEntity(userEntity));
    }
}