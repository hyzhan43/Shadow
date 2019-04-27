package com.example.core.service;

import com.example.core.bean.model.AuthModel;
import com.example.core.bean.card.RouteMetaCard;
import com.example.core.bean.db.Auth;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import com.example.core.repository.AuthRepository;
import com.example.core.utils.RouteMetaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
@Service
public class AuthService {

    private AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

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

    public List<Auth> getAuthByGroupId(Integer groupId) {
        return authRepository.findByGroupId(groupId);
    }

    public void deleteAuthByGroupId(Integer gid) {
        List<Auth> authList = getAuthByGroupId(gid);
        authRepository.deleteAll(authList);
    }

    public void createAuth(AuthModel model) {
        Auth auth = new Auth();
        BeanUtils.copyProperties(model, auth);
        authRepository.save(auth);
    }

    public void findAuthByGroupIdAndAuth(Integer groupId, String auth) {

        Optional<Auth> authOptional = authRepository.findByGroupIdAndAuth(groupId, auth);
        if (authOptional.isPresent()){
            throw new BaseException(ErrorCode.AUTH_IS_EXIST);
        }
    }

    @Transactional
    public void deleteAuths(Integer gid, List<String> auths) {
        authRepository.deleteByGroupIdAndAuthIn(gid, auths);
    }
}
