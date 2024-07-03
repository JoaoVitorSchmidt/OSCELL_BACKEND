package com.oscell.oscell.config.authentication;

import com.oscell.oscell.config.security.DataTokenJWT;
import com.oscell.oscell.config.security.TokenService;
import com.oscell.oscell.model.User;
import com.oscell.oscell.config.UserCredentials;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid UserCredentials credentials) {
        var AuthenticationToken = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        var authentication = manager.authenticate(AuthenticationToken);

        var tokenJWT = tokenService.geenerateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
