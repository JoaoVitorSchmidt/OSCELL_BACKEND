package com.oscell.oscell.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import com.oscell.oscell.domain.Part;
import com.oscell.oscell.domain.creation.PartCreation;
import com.oscell.oscell.domain.update.PartUpdate;

@Service
@Mapper
public class PartMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public PartMapper() {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull());
    }

    public Part map(final PartCreation partCreation) {
        return this.modelMapper.map(partCreation, Part.class);
    }

    public Part map(final PartUpdate partUpdate) {
        return this.modelMapper.map(partUpdate, Part.class);
    }
}
