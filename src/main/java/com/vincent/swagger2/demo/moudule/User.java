package com.vincent.swagger2.demo.moudule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "用戶實體類", description = "用戶訊息描述類")
public class User {

    @ApiModelProperty(value = "用戶id")
    private Integer id;

    @ApiModelProperty(value = "用戶名")
    private String username;

    @ApiModelProperty(value = "用戶密碼")
    private String password;
}
