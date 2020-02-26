package com.zlei.gp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-23 14:59
 */
@Mapper
@Component
public interface GoodsMapper {

    @Insert("insert into goods_info (picture) values (#{picture})")
    public void uploadPicture(String picture);

    @Insert("insert into goods_info (goodsName, description, price, picture, goodsUuid, userUuid) values (#{goodsName}, #{description}, #{price}, #{picture}, #{goodsUuid}, #{userUuid})")
    public void addGoods(String goodsName, String description, String price, String picture, String goodsUuid, String userUuid);

}
