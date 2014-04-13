package com.sdk.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.sdk.annotation.SDKTypeAPIImpl;
import com.sdk.constants.PlatformAPIType;
import com.sdk.service.base.AbstractSdkService;


@Component
public class SdkServiceFactory {

    @Resource
    List<AbstractSdkService> abstractSdkServiceList;

    private static Map<PlatformAPIType, AbstractSdkService> serviceMap = Maps.newHashMap();

    public AbstractSdkService getSdkService(PlatformAPIType apiType) {
        if (apiType == null) {
            return null;
        }
        return serviceMap.get(apiType);
    }

    @PostConstruct
    public void find() {
        for (AbstractSdkService abstractSdkService : abstractSdkServiceList) {
            Class<?> aClass = abstractSdkService.getClass();
            SDKTypeAPIImpl annotation = aClass.getAnnotation(SDKTypeAPIImpl.class);
            if (annotation != null) {
            	PlatformAPIType apiType = annotation.platformAPIType();
                serviceMap.put(apiType, abstractSdkService);
            }
        }
    }
}
