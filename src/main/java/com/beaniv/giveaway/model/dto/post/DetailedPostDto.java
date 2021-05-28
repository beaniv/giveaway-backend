package com.beaniv.giveaway.model.dto.post;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "Подробная информация о посте")
public class DetailedPostDto extends HomescreenPostDto {

    @ApiModelProperty(value = "Описание", required = true, example = "Разыгрываем шоколадку")
    private String description;

    @ApiModelProperty(value = "Условия", required = true, example = "Поставить 5 звезд приложению Giveaway")
    private String conditions;

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
