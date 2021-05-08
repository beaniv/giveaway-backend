package com.beaniv.giveaway.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Информация о пользователе для регистрации")
public class UserRegistrationDto extends UserDto {

    @ApiModelProperty(value = "Пароль", required = true, example = "SecretPassword")
    private String password;
}