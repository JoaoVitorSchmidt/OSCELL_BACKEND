package com.oscell.oscell.domain.endpoint;

import java.util.List;
import com.oscell.oscell.domain.repository.PartRepository;
import com.oscell.oscell.domain.mapper.PartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oscell.oscell.commons.response.ServiceOrderResponse;
import com.oscell.oscell.domain.creation.PartCreation;
import com.oscell.oscell.domain.update.PartUpdate;
import com.oscell.oscell.domain.Part;

@Service
public class PartEndpoint {
    @Autowired
    private PartRepository repository;

    @Autowired
    private PartMapper mapper;

    public List<Part> getParts() {
        return repository.findAll();
    }

    public Part getPart(Long sequence) throws Exception {
        try {
            return repository.findById(sequence).get();
        } catch (Exception e) {
            throw new Exception("Não encontrou a peça com o seguinte código: " + sequence);
        }
    }

    public ServiceOrderResponse<Part> createPart(PartCreation partCreation) {
        Part entity = mapper.map(partCreation);
        try {
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(entity, e.getMessage());
        }
    }

    public ServiceOrderResponse<Part> updatePart(Long sequence, PartUpdate partUpdate) {
        try {
            Part entity = getPart(sequence);
            if (partUpdate.getPartName() != null) {
                entity.setPartName(partUpdate.getPartName());
            }
            if (partUpdate.getPartQuantity() != null) {
                entity.setPartQuantity(partUpdate.getPartQuantity());
            }
            if (partUpdate.getPartCost() != null) {
                entity.setPartCost(partUpdate.getPartCost());
            }
            repository.save(entity);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.errorWithContent(mapper.map(partUpdate), e.getMessage());
        }
    }

    public ServiceOrderResponse<Part> deletePart(Long sequence) {
        try {
            Part entity = getPart(sequence);
            repository.deleteById(sequence);
            return ServiceOrderResponse.ok(entity);
        } catch (Exception e) {
            return ServiceOrderResponse.error(e.getMessage());
        }
    }
}
