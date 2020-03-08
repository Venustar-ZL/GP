package com.zlei.gp.entity;

import lombok.Data;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-03-03 10:38
 */
@Data
public class Goods {

    /**
     * 商品Uuid
     */
    private String goodsUuid;

    /**
     * 用户Uuid
     */
    private String userUuid;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String picture;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private Float price;

    /**
     * 发布人名称
     */
    private String userName;

    /**
     * 发布时间
     */
    private String createTime;

}
