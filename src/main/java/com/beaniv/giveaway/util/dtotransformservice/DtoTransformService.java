package com.beaniv.giveaway.util.dtotransformservice;

import com.beaniv.giveaway.model.dto.UserDto;
import com.beaniv.giveaway.model.entity.User;

public interface DtoTransformService {
    User convertToUser(UserDto userDto);
}