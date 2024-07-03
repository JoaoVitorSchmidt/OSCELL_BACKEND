package com.oscell.oscell.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.oscell.oscell.model.Client;
import com.oscell.oscell.model.creation.ClientCreation;
import com.oscell.oscell.model.update.ClientUpdate;

@Service
@Mapper
public class ClientMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ClientMapper() {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
    }
    
    public Client map(final ClientCreation clientCreation) {
        return this.modelMapper.map(clientCreation, Client.class);
    }

    public Client map(final ClientUpdate clientUpdate) {
        return this.modelMapper.map(clientUpdate, Client.class);
    }
}
