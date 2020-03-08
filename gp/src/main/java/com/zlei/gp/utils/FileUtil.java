package com.zlei.gp.utils;

import com.zlei.gp.entity.UploadResult;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-23 16:32
 */
@Component
public class FileUtil {

    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "120.26.88.216";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "root";
    //密码
    private static final String FTP_PASSWORD = "Zl199804046016";
    //路径都是/home/加上用户名  待定
    public static final String FTP_BASEPATH = "/usr/java/img";

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

    public static UploadResult uploadToLinux(MultipartFile file) {
        UploadResult uploadResult = new UploadResult();
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("utf-8");
        boolean success = false;
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);
            ftp.login(FTP_USERNAME, FTP_PASSWORD);
            reply = ftp.getReplyCode();
            System.out.println(reply);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(FTP_BASEPATH);

            String fileName = file.getOriginalFilename();//获取文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取文件的后缀名

            fileName = UuidUtil.getUUID() + suffixName;

            //上传的文件名也需要加上后缀，不然虚拟机不知道文件格式
            InputStream inputStream = file.getInputStream();
            String filePath = null;

            ftp.storeFile(fileName, inputStream);//这里开始上传文件
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.out.println("连接失败");
                success = true;
            }
            uploadResult.setFileName(fileName);
            System.out.println("连接成功！");

            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        uploadResult.setResult(success);
        return uploadResult;
    }
}
