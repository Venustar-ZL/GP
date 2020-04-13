package com.zlei.gp.mapper;

import com.zlei.gp.entity.Goods;
import com.zlei.gp.entity.GoodsUuids;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    /**
    * @Description: 上传图片
    * @Param: [picture]
    * @return: void
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Insert("insert into goods_info (picture) values (#{picture})")
    public void uploadPicture(String picture);

    /**
    * @Description: 添加商品
    * @Param: [goodsName, description, price, picture, goodsUuid, userUuid, userName, createTime]
    * @return: void
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Insert("insert into goods_info (goodsName, description, price, picture, goodsUuid, userUuid, userName, createTime) values (#{goodsName}, #{description}, #{price}, #{picture}, #{goodsUuid}, #{userUuid}, #{userName}, #{createTime})")
    public void addGoods(String goodsName, String description, String price, String picture, String goodsUuid, String userUuid, String userName, String createTime);

    /**
    * @Description: 由商品名称获取商品详情
    * @Param: [goodsName]
    * @return: java.util.List<com.zlei.gp.entity.Goods>
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Select("select goodsUuid, userUuid, goodsName, picture, description, price, userName, createTime from goods_info where goodsName = #{goodsName}")
    public List<Goods> getGoodsByName(String goodsName);

    /**
    * @Description: 获取所有商品
    * @Param: []
    * @return: java.util.List<com.zlei.gp.entity.Goods>
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Select("select goodsUuid, userUuid, goodsName, picture, description, price, userName, createTime from goods_info")
    public List<Goods> getGoods();

    /**
    * @Description: 由商品Uuid获取商品详情
    * @Param: [goodsUuid]
    * @return: com.zlei.gp.entity.Goods
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Select("select goodsUuid, userUuid, goodsName, picture, description, price, userName, createTime from goods_info where goodsUuid = #{goodsUuid}")
    public Goods getGoodsById(String goodsUuid);

    /**
    * @Description: 获取用户上传商品数量
    * @Param: [userUuid]
    * @return: java.lang.Integer
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Select("select count(*) from goods_info where userUuid = #{userUuid}")
    public Integer getUserPostCount(@Param("userUuid") String userUuid);

    /**
    * @Description: 添加购物车
    * @Param: [goodsUuid, userUuid]
    * @return: void
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Insert("insert into shopcar_info(goodsUuid, userUuid, createTime) values (#{goodsUuid}, #{userUuid}, #{createTime})")
    public void addShopCar(@Param("goodsUuid") String goodsUuid, @Param("userUuid") String userUuid, @Param("createTime") String createTime);

    /**
    * @Description: 显示该用户购物车
    * @Param: [userUuid]
    * @return: java.util.List<com.zlei.gp.entity.GoodsUuids>
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Select("select goodsUuid from shopcar_info where userUuid = #{userUuid}")
    public List<GoodsUuids> getGoodsUuidByUser(@Param("userUuid") String userUuid);

    /**
    * @Description: 获取该用户上传的商品
    * @Param: [userUuid]
    * @return: java.util.List<com.zlei.gp.entity.Goods>
    * @Author: ZhangLei
    * @Date: 2020/3/16
    */
    @Select("select goodsName, picture, description, price, userName, createTime from goods_info where userUuid = #{userUuid}")
    public List<Goods> getGoodsInfoByUser(@Param("userUuid") String userUuid);
    
    /**
    * @Description: 删除该用户上传的所有商品
    * @Param: [userUuid]
    * @return: void
    * @Author: ZhangLei
    * @Date: 2020/4/9
    */
    @Delete("delete from goods_info where userUuid = #{userUuid}")
    public void deleteAllGoodsByUser(@Param("userUuid") String userUuid);
    
    /**
    * @Description: 修改商品的上传用户名
    * @Param: [userName, userUuid]
    * @return: void
    * @Author: ZhangLei
    * @Date: 2020/4/9
    */
    @Select("update goods_info set userName = #{userName} where userUuid = #{userUuid}")
    public void updateUserInGoods(@Param("userName") String userName, @Param("userUuid") String userUuid);

    @Delete("delete from goods_info where goodsUuid = #{goodsUuid}")
    public void deleteGoodsInfo(@Param("goodsUuid") String goodsUuid);

}
