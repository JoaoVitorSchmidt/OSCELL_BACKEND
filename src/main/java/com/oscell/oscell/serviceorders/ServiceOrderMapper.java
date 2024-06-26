package com.oscell.oscell.serviceorders;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.oscell.oscell.serviceorders.domain.ServiceOrder;
import com.oscell.oscell.serviceorders.domain.ServiceOrderCreation;
import com.oscell.oscell.serviceorders.domain.ServiceOrderUpdate;

@Service
@Mapper
public class ServiceOrderMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ServiceOrderMapper() {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
    }
    
    public ServiceOrder map(final ServiceOrderCreation serviceOrderCreation) {
        return this.modelMapper.map(serviceOrderCreation, ServiceOrder.class);
    }

    public ServiceOrder map(final ServiceOrderUpdate serviceOrderUpdate) {
        return this.modelMapper.map(serviceOrderUpdate, ServiceOrder.class);
    }
}
