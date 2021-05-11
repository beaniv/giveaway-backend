package com.beaniv.giveaway.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@ApiModel(description = "JWT токен")
public class TokenDto {

    @ApiModelProperty(value = "JWT токен", required = true, example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdmFuX2l2YW5pY2gxMzM3QGdtY")
    private String token;
}