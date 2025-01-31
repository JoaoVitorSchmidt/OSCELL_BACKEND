package com.oscell.oscell.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.domain.endpoint.UserEndpoint;
import com.oscell.oscell.domain.User;
import com.oscell.oscell.domain.creation.UserCreation;
import com.oscell.oscell.domain.update.UserUpdate;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserEndpoint endpoint;

    @Autowired
    private AuthenticationManager manager;

    public UserController(UserEndpoint endpoint) {
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

    @PostMapping
    public ResponseEntity<ServiceOrderResponse<User>> createUser(@RequestBody UserCreation userCreation) {
        ServiceOrderResponse<User> response = endpoint.createUser(userCreation);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @PutMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<User>> updateUser(@PathVariable Long sequence, @RequestBody UserUpdate userUpdate) {
        ServiceOrderResponse<User> response = endpoint.updateUser(sequence, userUpdate);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<User>> deleteUser(@PathVariable Long sequence) {
        ServiceOrderResponse<User> response = endpoint.deleteUser(sequence);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }
}