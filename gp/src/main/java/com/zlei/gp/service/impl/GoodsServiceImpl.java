package com.zlei.gp.service.impl;

import com.zlei.gp.entity.*;
import com.zlei.gp.mapper.GoodsMapper;
import com.zlei.gp.mapper.SortMapper;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.GoodsService;
import com.zlei.gp.utils.FileUtil;
import com.zlei.gp.utils.TimeUtil;
import com.zlei.gp.utils.UuidUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-23 15:00
 */
@Service
@SuppressWarnings("all")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SortMapper sortMapper;

    @Override
    public CommonResult uploadGoodsPicture(MultipartFile file) {

//        String path = "D:/File/";
//        String fileName = file.getOriginalFilename();
//        boolean msg = FileUtil.upload(file, path, fileName);
//        if (msg == false) {
//            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "文件上传失败");
//        }

//        goodsMapper.uploadPicture(path + fileName);

        return CommonResult.buildWithData(ConstantEnum.GLOBAL_SUCCESS, null);
    }

    @Override
    public CommonResult addGoods(String goodsName, String description, String price, MultipartFile picture, String userUuid, String sort) {

        UploadResult uploadResult = FileUtil.uploadToLinux(picture);
        if (uploadResult.getResult() == false) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "文件上传失败");
        }
        String goodsUuid = UuidUtil.getUUID();

        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(date);

        User userInfo = userMapper.getUserInfoById(userUuid);
        String userName = userInfo.getUserName();

        // 生成图片路径
        String fileName = "http://120.26.88.216:8080/img/" + uploadResult.getFileName();

        goodsMapper.addGoods(goodsName, description, price, fileName, goodsUuid, userUuid, userName, createTime);

        int goodsCount = goodsMapper.getUserPostCount(userUuid);
        userMapper.updateCount(goodsCount, userUuid);

        // 将商品添加至分类表
        String sortType = sort;
        sortMapper.insertIntoSort(sortType, goodsUuid);

        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, null, "图片上传成功");
    }

    @Override
    public CommonResult showGoods(String goodsName) {

        List<Goods> goodsList = null;

        if (goodsName == null || goodsName.length() == 0) {
            goodsList = goodsMapper.getGoods();
        }
        else {
            goodsList = goodsMapper.getGoodsByName(goodsName);
        }

        if (goodsList.size() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "暂无商品信息");
        }

        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, goodsList, "查询成功");
    }

    @Override
    public CommonResult getGoodsInfoById(String goodsUuid) {
        Goods goods = goodsMapper.getGoodsById(goodsUuid);
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, goods, "查询成功");
    }

    @Override
    public CommonResult addShopCar(String goodsUuid, String userUuid) {
        String createTime = TimeUtil.getCurrentTime();
        goodsMapper.addShopCar(goodsUuid, userUuid, createTime);
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, null, "加入购物车成功");
    }

    @Override
    public CommonResult getShopCar(String userUuid) {
        List<Goods> shopCarList = new ArrayList<Goods>();
        List<GoodsUuids> goodsUuids = goodsMapper.getGoodsUuidByUser(userUuid);
        if (goodsUuids.size() != 0) {
        for (GoodsUuids goodsUuid : goodsUuids) {
            Goods goods = goodsMapper.getGoodsById(goodsUuid.getGoodsUuid());
            shopCarList.add(goods);
           }
        }
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, shopCarList, "查询购物车成功");
    }

    @Override
    public CommonResult getGoodsInfoByUser(String userUuid) {
        List<Goods> goodsList = goodsMapper.getGoodsInfoByUser(userUuid);
        if (goodsList == null || goodsList.size() == 0) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "该用户尚未发布闲置物品");
        }
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, goodsList, "查询成功");
    }

    @Override
    public CommonResult showGoodsBySort(String sortType) {
        List<Goods> goodsList = new ArrayList<>();
        if (Strings.isEmpty(sortType)) {
            goodsList = goodsMapper.getGoods();
        }
        else {
            List<Sort> sortList = sortMapper.getAllGoodsBySort(sortType);
            for (Sort sort : sortList) {
                String goodsUuid = sort.getGoodsUuid();
                Goods goods = goodsMapper.getGoodsById(goodsUuid);
                goodsList.add(goods);
            }
        }
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, goodsList, "查询成功");
    }

    @Override
    public CommonResult deleteGoods(String userUuid, String goodsUuid) {
        // 先删除商品信息
        goodsMapper.deleteGoodsInfo(goodsUuid);

        // 将用户的发布商品数量减1
        int goodsCount = goodsMapper.getUserPostCount(userUuid);
        userMapper.updateCount(goodsCount, userUuid);

        // 再删除分类表中对应的记录
        sortMapper.deleteGoodsInSort(goodsUuid);
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, null, "删除成功");
    }
}
