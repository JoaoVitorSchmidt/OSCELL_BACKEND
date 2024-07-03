package com.oscell.oscell.serviceorders;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.serviceorders.domain.ServiceOrder;
import com.oscell.oscell.serviceorders.domain.ServiceOrderCreation;
import com.oscell.oscell.serviceorders.domain.ServiceOrderUpdate;
import com.oscell.oscell.user.UserRepository;

@Service
public class ServiceOrderEndpoint {
    
    @Autowired
    ServiceOrderRepository repository;

    @Autowired
    ServiceOrderMapper mapper;

    @Autowired
    UserRepository userRepository;
    
    public List<ServiceOrder> getServiceOrder() {
        return repository.findAll();
    }

    public ServiceOrder getServiceOrder(Long sequence) throws Exception{
        try{
            return repository.findById(sequence).get();
        }catch(Exception e){
            throw new Exception("No service order found with the sequence: " + sequence);
        }
    }

    public ServiceOrderResponse<ServiceOrder> createServiceOrder(ServiceOrderCreation serviceOrderCreation) {
        try {
            ServiceOrder serviceOrder = new ServiceOrder();
            serviceOrder.setBrand(serviceOrderCreation.getBrand());
            serviceOrder.setModel(serviceOrderCreation.getModel());
            serviceOrder.setCreationDate(Instant.now());
            serviceOrder.setDescription(serviceOrderCreation.getDescription());
            serviceOrder.setClientSequence(serviceOrderCreation.getClientSequence());
            serviceOrder.setClientName(serviceOrderCreation.getClientName());
            serviceOrder.setClientCell(serviceOrderCreation.getClientCell());
            serviceOrder.setClientFixo(serviceOrderCreation.getClientFixo());
            serviceOrder.setClientCPF(serviceOrderCreation.getClientCPF());
            serviceOrder.setClientEmail(serviceOrderCreation.getClientEmail());
            serviceOrder.setSituation(serviceOrderCreation.getSituation());
            serviceOrder.setUserSys(serviceOrderCreation.getUserSys());

            ServiceOrder savedServiceOrder = repository.save(serviceOrder);

            return ServiceOrderResponse.ok(savedServiceOrder);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(mapper.map(serviceOrderCreation), e.getMessage());
        }
    }

    public ServiceOrderResponse<ServiceOrder> updateServiceOrder(Long sequence, ServiceOrderUpdate serviceOrderUpdate) {
        try {  
            ServiceOrder entity = getServiceOrder(sequence);
            if (serviceOrderUpdate.getBrand() != null) {
                entity.setBrand(serviceOrderUpdate.getBrand());
            }
            if (serviceOrderUpdate.getModel() != null) {
                entity.setModel(serviceOrderUpdate.getModel());
            }
            if (serviceOrderUpdate.getDescription() != null) {
                entity.setDescription(serviceOrderUpdate.getDescription());
            }
            if (serviceOrderUpdate.getClientName() != null) {
                entity.setClientName(serviceOrderUpdate.getClientName());
            }
            if (serviceOrderUpdate.getClientCell() != null) {
                entity.setClientCell(serviceOrderUpdate.getClientCell());
            }
            if (serviceOrderUpdate.getClientFixo() != null) {
                entity.setClientFixo(serviceOrderUpdate.getClientFixo());
            }
            if (serviceOrderUpdate.getClientCPF() != null) {
                entity.setClientCPF(serviceOrderUpdate.getClientCPF());
            }
            if (serviceOrderUpdate.getClientEmail() != null) {
                entity.setClientEmail(serviceOrderUpdate.getClientEmail());
            }
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(mapper.map(serviceOrderUpdate), e.getMessage());
        }
    }

    public ServiceOrderResponse<ServiceOrder> deleteServiceOrder(Long sequence) {
        try {
            ServiceOrder entity = getServiceOrder(sequence);
            repository.deleteById(sequence);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrder>> getServiceOrders() {
        List<ServiceOrder> serviceOrders = repository.findAll();
        return ResponseEntity.ok(serviceOrders);
    }
}
