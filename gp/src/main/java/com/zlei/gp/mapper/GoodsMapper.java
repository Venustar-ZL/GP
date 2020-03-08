package com.zlei.gp.mapper;

import com.zlei.gp.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Insert("insert into goods_info (goodsName, description, price, picture, goodsUuid, userUuid, userName, createTime) values (#{goodsName}, #{description}, #{price}, #{picture}, #{goodsUuid}, #{userUuid}, #{userName}, #{createTime})")
    public void addGoods(String goodsName, String description, String price, String picture, String goodsUuid, String userUuid, String userName, String createTime);

    @Select("select goodsUuid, userUuid, goodsName, picture, description, price, userName, createTime from goods_info where goodsName = #{goodsName}")
    public List<Goods> getGoodsByName(String goodsName);

    @Select("select goodsUuid, userUuid, goodsName, picture, description, price, userName, createTime from goods_info")
    public List<Goods> getGoods();

    @Select("select goodsUuid, userUuid, goodsName, picture, description, price, userName, createTime from goods_info where goodsUuid = #{goodsUuid}")
    public Goods getGoodsById(String goodsUuid);

}
