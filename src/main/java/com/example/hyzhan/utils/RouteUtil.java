package com.example.hyzhan.utils;

import com.example.hyzhan.annotation.RouteMeta;
import com.example.hyzhan.bean.model.RouteMetaModel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * author：  HyZhan
 * create：  2019/4/9
 * desc：    Spring容器加载所有配置的bean 处理类
 */
@Component
public class RouteUtil implements BeanPostProcessor {

    private static List<RouteMetaModel> routes = new ArrayList<>();

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
                    RouteMetaModel model = new RouteMetaModel();
                    model.setAuth(routeMeta.auth());
                    model.setModule(routeMeta.module());
                    model.setMount(routeMeta.mount());
                    routes.add(model);
                }
            }
        }
        return bean;
    }

    public static List<RouteMetaModel> getRoutes() {
        return routes;
    }
}
