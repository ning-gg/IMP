package com.sdk.utils;

import java.util.*;

import com.agapple.mapping.BeanMappingUtil;
import com.agapple.mapping.core.BeanMappingException;
import com.agapple.mapping.process.convertor.Convertor;
import com.agapple.mapping.process.convertor.ConvertorHelper;


public class BeanCopyUtils {

    private static ConvertorHelper helper = new ConvertorHelper();

    public static void copy(Object src, Object target) throws BeanMappingException {
        BeanMappingUtil.copy(src, target);
    }

    public static Map describe(Object src) throws BeanMappingException {
        return BeanMappingUtil.describe(src);
    }

    public static void populate(Object target, Map properties) throws BeanMappingException {
        BeanMappingUtil.populate(target, properties);
    }

    public static <T> List<T> convertFormListToList(List srcList, Class<T> targetClass) {
        Convertor convertor = helper.getConvertor(List.class, ArrayList.class);
        List<T> copyList = (List<T>) convertor.convertCollection(srcList, ArrayList.class, targetClass);
        return copyList;
    }

    public static <T> Set<T> convertFromListToSet(List srcList, Class<T> targetClass) {
        Convertor convertor = helper.getConvertor(List.class, HashSet.class);
        return (Set<T>) convertor.convertCollection(srcList, HashSet.class, targetClass);
    }
}