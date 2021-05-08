package com.beaniv.giveaway.util.dtotransformservice;

import com.beaniv.giveaway.model.dto.UserDto;
import com.beaniv.giveaway.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultDtoTransformService implements DtoTransformService {
    private final ModelMapper modelMapper;

    @Override
    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}