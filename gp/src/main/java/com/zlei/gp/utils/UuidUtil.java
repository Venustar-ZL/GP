package com.zlei.gp.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-25 11:07
 */
public class UuidUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String checkEmptyAndGetUUID(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            return getUUID();
        }
        return uuid;
    }
}
