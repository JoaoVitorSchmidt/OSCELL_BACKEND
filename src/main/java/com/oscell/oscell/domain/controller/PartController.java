package com.oscell.oscell.domain.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.domain.endpoint.PartEndpoint;
import com.oscell.oscell.domain.creation.PartCreation;
import com.oscell.oscell.domain.update.PartUpdate;
import com.oscell.oscell.domain.Part;

@RestController
@RequestMapping("/part")
@CrossOrigin(origins = "http://localhost:3000")
public class PartController {
    @Autowired
    private PartEndpoint endpoint;

    public PartController(PartEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping
    public ResponseEntity<List<Part>> getParts() {
        List<Part> parts = endpoint.getParts();
        return ResponseEntity.ok().body(parts);
    }

    @GetMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<Part>> getPart(@PathVariable Long sequence) {
        try {
            Part part = endpoint.getPart(sequence);
            return ResponseEntity.ok().body(ServiceOrderResponse.ok(part));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ServiceOrderResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse<Part>> createPart(@RequestBody PartCreation partCreation) {
        ServiceOrderResponse<Part> response = endpoint.createPart(partCreation);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @PutMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<Part>> updatePart(@PathVariable Long sequence, @RequestBody PartUpdate partUpdate) {
        ServiceOrderResponse<Part> response = endpoint.updatePart(sequence, partUpdate);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<Part>> deletePart(@PathVariable Long sequence) {
        ServiceOrderResponse<Part> response = endpoint.deletePart(sequence);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }
}
