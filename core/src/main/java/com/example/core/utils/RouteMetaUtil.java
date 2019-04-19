package com.example.core.utils;

import com.example.core.annotation.RouteMeta;
import com.example.core.bean.card.RouteMetaCard;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
                RouteMeta routeMeta = AnnotationUtils.findAnnotation(method, RouteMeta.class);
                if (routeMeta != null) {
                    RouteMetaCard model = new RouteMetaCard();
                    model.setAuth(routeMeta.auth());
                    model.setModule(routeMeta.module());
                    model.setMount(routeMeta.mount());
                    // 方法名为 key, 方便后续 判断权限组
                    routeMetaCardMap.put(method.getName(), model);
                }
            }
        }
        return bean;
    }

    public static Map<String, RouteMetaCard> getRouteMetaCardMap() {
        return routeMetaCardMap;
    }
}
