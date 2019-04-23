package com.example.core.service.auth;

import com.example.core.bean.card.RouteMetaCard;
import com.example.core.bean.db.Auth;
import com.example.core.exception.BaseException;
import com.example.core.exception.code.ErrorCode;
import com.example.core.repository.AuthRepository;
import com.example.core.utils.RouteMetaUtil;
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

        if (!authOptional.isPresent()) {
            throw new BaseException(ErrorCode.AUTH_ERROR);
        }
    }

    public List<Auth> getAuthsByGroupId(Integer groupId) {

        List<Auth> auths = authRepository.findByGroupId(groupId);

        return auths;
    }
}
