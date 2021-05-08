package com.beaniv.giveaway.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Данные для входа")
public class Credentials {
    @ApiModelProperty(value = "Логин пользователя", required = true, example = "ivan@gmail.com")
    private String login;

    @ApiModelProperty(value = "Пароль", required = true, example = "SecretPassword")
    private String password;
}
