package com.chaoyue.common.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/9/25 23:09
 * @version: version 1.0
 * @dec: 描述信息
 */
public class BeanCopierUtil {
    public static Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap();

    public BeanCopierUtil() {
    }

    public static void copyProperties(Object source, Object target) {
        String beanCopierMapKey = buildKey(source.getClass(), target.getClass(), Boolean.FALSE);

        try {
            BeanCopier beanCopier;
            if (beanCopierMap.containsKey(beanCopierMapKey)) {
                beanCopier = beanCopierMap.get(beanCopierMapKey);
            } else {
                beanCopier = BeanCopier.create(source.getClass(), target.getClass(), Boolean.FALSE);
                beanCopierMap.put(buildKey(source.getClass(), target.getClass(), Boolean.FALSE), beanCopier);
            }

            beanCopier.copy(source, target, null);
        } catch (Exception var5) {
        }

    }

    public static void copyProperties(Object source, Object target, Converter converter) {
        String beanCopierMapKey = buildKey(source.getClass(), target.getClass(), Boolean.TRUE);

        try {
            BeanCopier beanCopier;
            if (beanCopierMap.containsKey(beanCopierMapKey)) {
                beanCopier = beanCopierMap.get(beanCopierMapKey);
            } else {
                beanCopier = BeanCopier.create(source.getClass(), target.getClass(), Boolean.TRUE);
                beanCopierMap.put(buildKey(source.getClass(), target.getClass(), Boolean.TRUE), beanCopier);
            }

            beanCopier.copy(source, target, converter);
        } catch (Exception var6) {
        }

    }

    public static List BatchCopyProperties(List sources, Class clazz) {
        if (sources == null || sources.isEmpty()) {
            return Collections.emptyList();
        } else {
            List targets = new ArrayList(sources.size());
            Iterator var3 = sources.iterator();

            while (var3.hasNext()) {
                Object source = var3.next();
                String beanCoPierMapKey = buildKey(source.getClass(), clazz, Boolean.FALSE);

                try {
                    BeanCopier beanCopier;
                    if (beanCopierMap.containsKey(beanCoPierMapKey)) {
                        beanCopier = beanCopierMap.get(beanCoPierMapKey);
                    } else {
                        beanCopier = BeanCopier.create(source.getClass(), clazz, Boolean.FALSE);
                        beanCopierMap.put(buildKey(source.getClass(), clazz, Boolean.FALSE), beanCopier);
                    }

                    Object target = clazz.newInstance();
                    targets.add(target);
                    beanCopier.copy(source, target, null);
                } catch (Exception var8) {
                }
            }

            return targets;
        }
    }

    private static String buildKey(Class<?> sourceClass, Class<?> targetClass, Boolean needConverter) {
        StringBuilder sb = new StringBuilder();
        sb.append(sourceClass.toString()).append(targetClass.toString()).append(needConverter.toString());
        return sb.toString();
    }
}
