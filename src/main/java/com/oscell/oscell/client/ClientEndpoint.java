package com.oscell.oscell.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.client.domain.Client;
import com.oscell.oscell.client.domain.ClientCreation;
import com.oscell.oscell.client.domain.ClientUpdate;

@Service
public class ClientEndpoint {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientMapper mapper;

    public List<Client> getClient() {
        return repository.findAll();
    }

    public Client getClient(Long sequence) throws Exception{
        try{
            return repository.findById(sequence).get();
        }catch(Exception e){
            throw new Exception("No client found with the sequence: " + sequence);
        }
    }

    public ServiceOrderResponse<Client> createClient(ClientCreation clientCreation) {
        Client entity = mapper.map(clientCreation);
        try {
            repository.save(entity);
            
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(entity, e.getMessage());
        }
    }

    public ServiceOrderResponse<Client> updateClient(Long sequence, ClientUpdate clientUpdate) {
        try {  
            Client entity = getClient(sequence);
            if (clientUpdate.getClientName() != null) {
                entity.setClientName(clientUpdate.getClientName());
            }
            if (clientUpdate.getClientCell() != null) {
                entity.setClientCell(clientUpdate.getClientCell());
            }
            if (clientUpdate.getClientFixo() != null) {
                entity.setClientFixo(clientUpdate.getClientFixo());
            }
            if (clientUpdate.getClientCPF() != null) {
                entity.setClientCPF(clientUpdate.getClientCPF());
            }
            if (clientUpdate.getClientCNPJ() != null) {
                entity.setClientCPF(clientUpdate.getClientCNPJ());
            }
            if (clientUpdate.getClientEmail() != null) {
                entity.setClientEmail(clientUpdate.getClientEmail());
            }
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(mapper.map(clientUpdate), e.getMessage());
        }
    }

    public ServiceOrderResponse<Client> deleteClient(Long sequence) {
        try {
            Client entity = getClient(sequence);
            repository.deleteById(sequence);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }
}
