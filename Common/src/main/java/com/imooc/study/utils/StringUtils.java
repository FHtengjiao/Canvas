package com.imooc.study.utils;

public class StringUtils {

    public static boolean isNotEmpty(String target) {
        return !"".equals(target) && null != target;
    }
}
