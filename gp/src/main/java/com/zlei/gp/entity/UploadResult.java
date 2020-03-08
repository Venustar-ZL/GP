package com.zlei.gp.entity;

import lombok.Data;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-03-07 20:11
 */
@Data
public class UploadResult {

    /**
     * 返回结果
     */
    private Boolean result;

    /**
     * 文件名
     */
    private String fileName;

}
