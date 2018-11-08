package com.imooc.study.utils;

import java.util.UUID;

public class StringUtils {

    public static boolean isNotEmpty(String target) {
        return !"".equals(target) && null != target;
    }

    public static String getRandomFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + fileName.substring(index);
    }
}
