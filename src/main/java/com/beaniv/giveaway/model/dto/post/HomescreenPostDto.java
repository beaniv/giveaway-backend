package com.beaniv.giveaway.model.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Информация о посте для отображения на homescreen приложения")
public class HomescreenPostDto {
    @ApiModelProperty(value = "ID поста", required = true, example = "1")
    private int id;

    @ApiModelProperty(value = "Заголовок", required = true, example = "Розыгрыш")
    private String title;

    @ApiModelProperty(value = "Приз", required = true, example = "Шоколадка")
    private String prize;

    @ApiModelProperty(value = "URL аватара", required = true, example = "https://somesite.com/somepicture")
    private String imageUrl;
}
