package com.zlei.gp.mapper;

import com.zlei.gp.entity.Sort;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @Description: 分类
* @Param:
* @return:
* @Author: ZhangLei
* @Date: 2020/4/13
*/
@Component
@Mapper
public interface SortMapper {

    @Insert("insert into sort_info (sortType, goodsUuid) values (#{sortType}, #{goodsUuid})")
    void insertIntoSort(@Param("sortType") String sortType, @Param("goodsUuid") String goodsUuid);

    @Select("select sortType, goodsUuid from sort_info where sortType = #{sortType}")
    List<Sort> getAllGoodsBySort(@Param("sortType") String sortType);

    @Delete("delete from sort_info where goodsUuid = #{goodsUuid}")
    void deleteGoodsInSort(@Param("goodsUuid") String goodsUuid);
}
