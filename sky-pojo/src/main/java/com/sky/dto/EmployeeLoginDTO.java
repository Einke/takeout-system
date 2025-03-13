package com.sky.dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

@Data
@Tag(name = "员工登录时传递的数据模型")
//@ApiModel(description = "员工登录时传递的数据模型")
public class EmployeeLoginDTO implements Serializable {

    //@ApiModelProperty("用户名")
    @Schema(description = "用户名")
    private String username;

    //@ApiModelProperty("密码")
    @Schema(description = "密码")
    private String password;

}
