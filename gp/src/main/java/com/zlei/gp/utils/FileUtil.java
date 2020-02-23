package com.zlei.gp.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-23 16:32
 */
public class FileUtil {

    /**
     *
     * @param file 文件
     * @param path 存放路径
     * @param fileName 原文件名
     * @return
     */
    public static boolean upload (MultipartFile file, String path, String fileName) {
        String realpath = path + "/" + fileName;
        File dest = new File(realpath);

        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            // 保存文件
            file.transferTo(dest);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
