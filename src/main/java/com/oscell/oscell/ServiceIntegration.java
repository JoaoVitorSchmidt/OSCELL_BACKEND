package com.oscell.oscell;

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
import com.oscell.oscell.service.ServiceEndpoint;
import com.oscell.oscell.service.domain.ServiceCreation;
import com.oscell.oscell.service.domain.ServiceUpdate;
import com.oscell.oscell.service.domain.Services;

@RestController
@RequestMapping("/services")
public class ServiceIntegration {
    @Autowired
    private ServiceEndpoint endpoint;

    public ServiceIntegration(ServiceEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping
    public ResponseEntity<List<Services>> getService() {
        List<Services> services = endpoint.getService();
        return ResponseEntity.ok().body(services);
    }

    @GetMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<Services>> getService(@PathVariable Long sequence) {
        try{
            Services service = endpoint.getService(sequence);
            return ResponseEntity.ok().body(ServiceOrderResponse.ok(service));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(ServiceOrderResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse<Services>> createService(@RequestBody ServiceCreation serviceCreation) {
        ServiceOrderResponse<Services> response = endpoint.createService(serviceCreation);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @PutMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<Services>> updateService(@PathVariable Long sequence, @RequestBody ServiceUpdate serviceUpdate) {
        ServiceOrderResponse<Services> response = endpoint.updateService(sequence, serviceUpdate);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<Services>> deleteService(@PathVariable Long sequence) {
        ServiceOrderResponse<Services> response = endpoint.deleteService(sequence);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

}
