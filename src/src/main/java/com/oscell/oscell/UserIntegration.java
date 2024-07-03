package com.oscell.oscell;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.user.UserEndpoint;
import com.oscell.oscell.user.domain.User;
import com.oscell.oscell.user.domain.UserCreation;
import com.oscell.oscell.user.domain.UserUpdate;

@RestController
@RequestMapping("/user")
public class UserIntegration {
    @Autowired
    UserEndpoint endpoint;

    public UserIntegration(UserEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        List<User> users = endpoint.getUser();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<User>> getUser(@PathVariable Long sequence) {
        try {
            User user = endpoint.getUser(sequence);
            return ResponseEntity.ok().body(ServiceOrderResponse.ok(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ServiceOrderResponse.error(e.getMessage()));
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<ServiceOrderResponse<String>> loginUser(@RequestBody UserCredentials credentials) {
        try {
            String jwtToken = endpoint.authenticateUser(credentials.getUserName(), credentials.getPassword());
            return ResponseEntity.ok().body(ServiceOrderResponse.ok(jwtToken));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ServiceOrderResponse.error("Credenciais inválidas."));
        }
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse<User>> createUser(@RequestBody UserCreation userCreation) {
        ServiceOrderResponse<User> response = endpoint.createUser(userCreation);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @PutMapping("/{searchKey}/{searchValue}")
    public ResponseEntity<ServiceOrderResponse<User>> updateUser(@PathVariable String searchKey, @PathVariable String searchValue, @RequestBody UserUpdate userUpdate) {
        ServiceOrderResponse<User> response = endpoint.updateUser(searchKey, searchValue, userUpdate);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<User>> deleteUser(@PathVariable Long sequence) {
        ServiceOrderResponse<User> response = endpoint.deleteUser(sequence);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }
}