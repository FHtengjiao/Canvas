package com.imooc.study.utils;

import java.util.UUID;

public class StringUtils {

    /**
     * 判断字符是否为Null或者""
     * */
    public static boolean isNotEmpty(String target) {
        return !"".equals(target) && null != target;
    }

    /**
     * 获取随机文件名
     * */
    public static String getRandomFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + fileName.substring(index);
    }
}
