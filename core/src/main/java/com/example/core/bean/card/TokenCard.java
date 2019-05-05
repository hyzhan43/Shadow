package com.example.core.bean.card;

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

    private String access_token;

    private String refresh_token;
}
