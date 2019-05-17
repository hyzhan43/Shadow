package com.example.core.bean.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Data
@ApiModel
@AllArgsConstructor
public class TokenCard {

    @JsonProperty("access_token")
    @ApiModelProperty("令牌")
    private String accessToken;

    @JsonProperty("refresh_token")
    @ApiModelProperty("刷新令牌")
    private String refreshToken;
}
