package com.zhan.core.bean.card;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Data
@AllArgsConstructor
public class TokenCard {

    private String accessToken;

    private String refreshToken;
}
