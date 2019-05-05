package com.example.core.utils;

import com.example.core.annotation.RouteMeta;
import com.example.core.bean.card.RouteMetaCard;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * author：  HyZhan
 * create：  2019/4/9
 * desc：    Spring容器加载所有配置的bean 处理类
 */
@Component
public class RouteMetaUtil implements BeanPostProcessor {

    private static Map<String, RouteMetaCard> routeMetaCardMap = new HashMap<>();

    /**
     * postProcessAfterInitialization 是在bean加载之后进行的操作
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods != null) {
            for (Method method : methods) {
                if (method.isAnnotationPresent(RouteMeta.class)) {
                    RouteMeta routeMeta = method.getDeclaredAnnotation(RouteMeta.class);

                    RouteMetaCard model = new RouteMetaCard();
                    model.setAuth(routeMeta.auth());
                    model.setModule(routeMeta.module());
                    model.setMount(routeMeta.mount());

                    // 获取当前方法的 class
                    Class currentClass = method.getDeclaringClass();
                    if (currentClass.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping mapping = (RequestMapping) currentClass.getAnnotation(RequestMapping.class);
                        String[] url = mapping.value();
                        String key = Arrays.toString(url) + "+" + method.getName();
                        // 方法名为 key, 方便后续 判断权限组
                        routeMetaCardMap.put(key, model);
                    }
                }
            }
        }
        return bean;
    }

    public static Map<String, RouteMetaCard> getRouteMetaCardMap() {
        return routeMetaCardMap;
    }

    public static Map<String, Map<String, List<String>>> getAuthorityInfo() {
        Map<String, Map<String, List<String>>> infos = new HashMap<>();

        routeMetaCardMap.forEach((method, routeMetaCard) -> {
            if (routeMetaCard.isMount()) {
                String module = routeMetaCard.getModule();
                String auth = routeMetaCard.getAuth();

                List<String> methodList;
                Map<String, List<String>> authMap;

                if (infos.containsKey(module)) {
                    authMap = infos.get(module);

                    if (authMap.containsKey(auth)) {
                        methodList = authMap.get(auth);
                        methodList.add(method);
                        authMap.put(auth, methodList);
                    } else {
                        methodList = new ArrayList<>();
                        methodList.add(method);
                        authMap.put(auth, methodList);
                    }
                } else {
                    authMap = new HashMap<>();
                    methodList = new ArrayList<>();
                    methodList.add(method);
                    authMap.put(auth, methodList);
                    infos.put(module, authMap);
                }
            }
        });

        return infos;
    }

    public static RouteMetaCard getRouteMetaCard(String key) {
        return routeMetaCardMap.get(key);
    }

    public static RouteMetaCard getRouteMetaCardByAuth(String auth) {

        RouteMetaCard[] routeMetaCards = new RouteMetaCard[]{};
        routeMetaCards = routeMetaCardMap.values().toArray(routeMetaCards);

        RouteMetaCard card = null;
        for (RouteMetaCard routeMetaCard : routeMetaCards) {
            if (routeMetaCard.getAuth().equals(auth)) {
                card = routeMetaCard;
                break;
            }
        }

        if (card == null)
            throw new BaseException(ErrorCode.ROUTE_META_NOT_EXIST);

        return card;
    }
}
