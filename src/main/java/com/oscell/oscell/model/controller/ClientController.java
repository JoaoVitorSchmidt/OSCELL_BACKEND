package com.oscell.oscell.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.oscell.oscell.model.endpoint.ClientEndpoint;
import com.oscell.oscell.model.Client;
import com.oscell.oscell.model.creation.ClientCreation;
import com.oscell.oscell.model.update.ClientUpdate;

@RestController
@RequestMapping("/client")
public class ClientController {
        @Autowired
        ClientEndpoint endpoint;

        public ClientController(ClientEndpoint endpoint) {
            this.endpoint = endpoint;
        }

        @GetMapping
        public ResponseEntity<List<Client>> getClient() {
            List<Client> clients = endpoint.getClient();
            return ResponseEntity.ok().body(clients);
        }

        @GetMapping("/{sequence}")
        public ResponseEntity<ServiceOrderResponse<Client>> getClient(@PathVariable Long sequence) {
            try{
                Client client = endpoint.getClient(sequence);
                return ResponseEntity.ok().body(ServiceOrderResponse.ok(client));
            }catch(Exception e){
                return ResponseEntity.badRequest().body(ServiceOrderResponse.error(e.getMessage()));
            }
        }

        @PostMapping
        public ResponseEntity<ServiceOrderResponse<Client>> createClient(@RequestBody ClientCreation clientCreation) {
            ServiceOrderResponse<Client> response = endpoint.createClient(clientCreation);
            return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
        }

        @PutMapping("/{sequence}")
        public ResponseEntity<ServiceOrderResponse<Client>> updateClient(@PathVariable Long sequence, @RequestBody ClientUpdate clientUpdate) {
            ServiceOrderResponse<Client> response = endpoint.updateClient(sequence, clientUpdate);
            return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
        }

        @DeleteMapping("/{sequence}")
        public ResponseEntity<ServiceOrderResponse<Client>> deleteClient(@PathVariable Long sequence) {
            ServiceOrderResponse<Client> response = endpoint.deleteClient(sequence);
            return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
        }
}
