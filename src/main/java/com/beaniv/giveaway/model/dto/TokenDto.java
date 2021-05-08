package com.beaniv.giveaway.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "JWT токен")
public class TokenDto {

    @ApiModelProperty(value = "JWT токен", required = true, example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpdmFuX2l2YW5pY2gxMzM3QGdtYWlsLmNvbSIsInJvbGVzIjpbIlVOQ09ORklSTUVEIl0sImlhdCI6MTU4OTU1MDc2NCwiZXhwIjoxNTg5NTU0MzY0fQ.6tULcBwHeXLafr42QpjlkMgmtfRHAIVoPuvOE03IjxJT0qESDfm2iOf82VXdyPmQX6FABpQaRUS_KmsAk1e9Rg")
    private final String token;
}