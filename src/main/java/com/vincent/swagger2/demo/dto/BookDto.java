package com.vincent.swagger2.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "書本資料")
public class BookDto {

    @ApiModelProperty(value = "序號", required = true)
    private Integer bookId;

    @ApiModelProperty(value = "書名", required = true)
    private String name;

    @ApiModelProperty(value = "作者", required = true)
    private String author;
}
