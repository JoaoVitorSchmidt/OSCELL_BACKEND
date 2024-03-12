package com.oscell.oscell.serviceorders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.serviceorders.domain.ServiceOrder;
import com.oscell.oscell.serviceorders.domain.ServiceOrderCreation;
import com.oscell.oscell.serviceorders.domain.ServiceOrderUpdate;

@Service
public class ServiceOrderEndpoint {
    @Autowired
    ServiceOrderRepository repository;
    @Autowired
    ServiceOrderMapper mapper;
    
    public List<ServiceOrder> getServiceOrder() {
        return repository.findAll();
    }

    public ServiceOrder getServiceOrder(final Long sequence) throws Exception{
        try{
            return repository.findById(sequence).get();
        }catch(Exception e){
            throw new Exception("No service order found with the sequence: " + sequence);
        }
    }

    public ServiceOrderResponse<ServiceOrder> createServiceOrder(final ServiceOrderCreation serviceOrderCreation) {
        ServiceOrder entity = mapper.map(serviceOrderCreation);
        
        try {
            repository.save(entity);
            
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(entity, e.getMessage());
        }
    }

    public ServiceOrderResponse<ServiceOrder> updateServiceOrder(final Long sequence, final ServiceOrderUpdate serviceOrderUpdate) {
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

    public ServiceOrderResponse<ServiceOrder> deleteServiceOrder(final Long sequence) {
        try {
            ServiceOrder entity = getServiceOrder(sequence);
            repository.deleteById(sequence);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }
}
