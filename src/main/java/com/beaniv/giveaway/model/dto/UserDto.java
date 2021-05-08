package com.beaniv.giveaway.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Общая информация о пользователе")
public class UserDto {

    @ApiModelProperty(value = "Электронная почта", required = true, example = "ivan@gmail.com")
    private String email;
}
