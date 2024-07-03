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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.serviceorders.ServiceOrderEndpoint;
import com.oscell.oscell.serviceorders.domain.ServiceOrder;
import com.oscell.oscell.serviceorders.domain.ServiceOrderCreation;
import com.oscell.oscell.serviceorders.domain.ServiceOrderUpdate;

@RestController
@RequestMapping("/serviceOrder")
public class ServiceOrderIntegration {
    @Autowired
    ServiceOrderEndpoint endpoint;

    public ServiceOrderIntegration(ServiceOrderEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrder>> getServiceOrder() {
        List<ServiceOrder> serviceOrders = endpoint.getServiceOrder();
        return ResponseEntity.ok().body(serviceOrders);
    }

    @GetMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<ServiceOrder>> getServiceOrder(@PathVariable Long sequence) {
        try{
            ServiceOrder serviceOrder = endpoint.getServiceOrder(sequence);
            return ResponseEntity.ok().body(ServiceOrderResponse.ok(serviceOrder));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(ServiceOrderResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponse<ServiceOrder>> createServiceOrder(
            @RequestBody ServiceOrderCreation serviceOrderCreation,
            @RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        ServiceOrderResponse<ServiceOrder> response = endpoint.createServiceOrder(serviceOrderCreation);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @PutMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<ServiceOrder>> updateServiceOrder(@PathVariable Long sequence, @RequestBody ServiceOrderUpdate serviceOrderUpdate) {
        ServiceOrderResponse<ServiceOrder> response = endpoint.updateServiceOrder(sequence, serviceOrderUpdate);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity<ServiceOrderResponse<ServiceOrder>> deleteServiceOrder(@PathVariable Long sequence) {
        ServiceOrderResponse<ServiceOrder> response = endpoint.deleteServiceOrder(sequence);
        return ResponseEntity.status(response.isError() ? 400 : 200).body(response);
    }
}
