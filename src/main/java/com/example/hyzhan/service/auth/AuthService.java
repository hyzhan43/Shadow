package com.example.hyzhan.service.auth;

import com.example.hyzhan.bean.card.RouteMetaCard;
import com.example.hyzhan.bean.db.cms.Auth;
import com.example.hyzhan.exception.BaseException;
import com.example.hyzhan.exception.code.ErrorCode;
import com.example.hyzhan.repository.AuthRepository;
import com.example.hyzhan.utils.RouteMetaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Service
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    public void isUserAllowed(Integer groupId, String methodName) {

        RouteMetaCard routeMetaModel = RouteMetaUtil.getRouteMetaCardMap()
                .get(methodName);

        if (routeMetaModel == null)
            throw new BaseException(ErrorCode.AUTH_ERROR);

        Optional<Auth> authOptional = authRepository.findByGroupIdAndAuthAndModule(
                groupId,
                routeMetaModel.getAuth(),
                routeMetaModel.getModule());

        if (!authOptional.isPresent()){
            throw new BaseException(ErrorCode.AUTH_ERROR);
        }
    }
}
