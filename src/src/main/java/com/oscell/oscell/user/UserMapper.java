package com.oscell.oscell.user;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.oscell.oscell.user.domain.User;
import com.oscell.oscell.user.domain.UserCreation;
import com.oscell.oscell.user.domain.UserUpdate;

@Service
@Mapper
public class UserMapper {
        private final ModelMapper modelMapper = new ModelMapper();

    public UserMapper() {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
    }
    
    public User map(final UserCreation userCreation) {
        return this.modelMapper.map(userCreation, User.class);
    }

    public User map(final UserUpdate userUpdate) {
        return this.modelMapper.map(userUpdate, User.class);
    }
}
