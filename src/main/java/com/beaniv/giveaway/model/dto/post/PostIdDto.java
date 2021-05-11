package com.beaniv.giveaway.model.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "ID поста")
public class PostIdDto {
    @ApiModelProperty(value = "ID поста", required = true, example = "1")
    private int postID;
}