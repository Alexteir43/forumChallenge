package alura.forumchallenge.controller;

import alura.forumchallenge.infra.security.JWTTokenData;
import alura.forumchallenge.infra.security.TokenService;
import alura.forumchallenge.model.client.Client;
import alura.forumchallenge.model.client.ClientAuthenticationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateClient(@RequestBody @Valid ClientAuthenticationData data){
        Authentication authToken = new UsernamePasswordAuthenticationToken(data.login(), data.pass());
        var authenticatedClient = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generateToken((Client) authenticatedClient.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTtoken));
    }
}
