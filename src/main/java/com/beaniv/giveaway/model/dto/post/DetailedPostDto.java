package com.beaniv.giveaway.model.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "Подробная информация о посте")
public class DetailedPostDto {
    @ApiModelProperty(value = "ID поста", required = true, example = "1")
    private int id;

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

    @ApiModelProperty(value = "Дата и время окончания", required = true, example = "11/09/2001 8:46:00")
    private Timestamp finishTime;

    @ApiModelProperty(value = "Является ли пользователь участником", required = true, example = "true")
    private boolean isParticipant;

    @ApiModelProperty(value = "Является ли пользователь создателем", required = true, example = "true")
    private boolean isCreator;

    @ApiModelProperty(value = "Индикатор завершения", required = true, example = "true")
    private boolean isFinished;

    @ApiModelProperty(value = "ID победителя конкурса", required = true, example = "1")
    private int winnerId;
}
