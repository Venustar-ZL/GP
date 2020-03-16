package com.zlei.gp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: TimeUtil
 * @Description: 时间工具类
 * @Date: 2020-03-16 09:30
 * @Author: ZhangLei
 * version: 1.0
 **/
public class TimeUtil {

    /**
    * @Description: 获取当前时间
    * @Param: []
    * @return: java.lang.String
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    private static String getCurrentTime () {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
