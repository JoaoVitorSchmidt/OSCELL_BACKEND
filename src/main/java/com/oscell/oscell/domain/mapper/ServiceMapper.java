package com.oscell.oscell.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.oscell.oscell.domain.Services;
import com.oscell.oscell.domain.creation.ServiceCreation;
import com.oscell.oscell.domain.update.ServiceUpdate;

@Service
@Mapper
public class ServiceMapper {
        private final ModelMapper modelMapper = new ModelMapper();

    public ServiceMapper() {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
    }
    
    public Services map(final ServiceCreation serviceCreation) {
        return this.modelMapper.map(serviceCreation, Services.class);
    }

    public Services map(final ServiceUpdate serviceUpdate) {
        return this.modelMapper.map(serviceUpdate, Services.class);
    }
}
