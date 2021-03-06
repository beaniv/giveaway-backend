package com.beaniv.giveaway.model.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "Информация о посте, необходимая для его создания")
public class RegisterPostDto {
    @ApiModelProperty(value = "Заголовок", required = true, example = "Розыгрыш")
    private String title;

    @ApiModelProperty(value = "Описание", required = true, example = "Разыгрываем шоколадку")
    private String description;

    @ApiModelProperty(value = "Условия", required = true, example = "Поставить 5 звезд приложению Giveaway")
    private String conditions;

    @ApiModelProperty(value = "Приз", required = true, example = "Шоколадка")
    private String prize;

    @ApiModelProperty(value = "URL аватара", required = true, example = "https://somesite.com/somepicture")
    private String imageUrl;

    @ApiModelProperty(value = "Время завершения", required = true, example = "12:00")
    private Timestamp finishTime;
}
