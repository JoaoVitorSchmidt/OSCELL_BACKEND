package com.oscell.oscell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.service.domain.ServiceCreation;
import com.oscell.oscell.service.domain.ServiceUpdate;
import com.oscell.oscell.service.domain.Services;

@Service
public class ServiceEndpoint {
    @Autowired
    private ServiceRepository repository;

    @Autowired
    private ServiceMapper mapper;

    public List<Services> getService() {
        return repository.findAll();
    }

    public Services getService(Long sequence) throws Exception{
        try{
            return repository.findById(sequence).get();
        }catch(Exception e){
            throw new Exception("No service found with the sequence: " + sequence);
        }
    }

    public ServiceOrderResponse<Services> createService(ServiceCreation serviceCreation) {
        Services entity = mapper.map(serviceCreation);
        try {
            repository.save(entity);
            
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(entity, e.getMessage());
        }
    }

    public ServiceOrderResponse<Services> updateService(Long sequence, ServiceUpdate serviceUpdate) {
        try {  
            Services entity = getService(sequence);
            if (serviceUpdate.getServiceName() != null) {
                entity.setServiceName(serviceUpdate.getServiceName());
            }
            if (serviceUpdate.getServiceDescription() != null) {
                entity.setServiceDescription(serviceUpdate.getServiceDescription());
            }
            if (serviceUpdate.getServiceValue() != null) {
                entity.setServiceValue(serviceUpdate.getServiceValue());
            }
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(mapper.map(serviceUpdate), e.getMessage());
        }
    }

    public ServiceOrderResponse<Services> deleteService(Long sequence) {
        try {
            Services entity = getService(sequence);
            repository.deleteById(sequence);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }
}
