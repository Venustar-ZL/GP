package com.zlei.gp.service.impl;

import com.zlei.gp.entity.Goods;
import com.zlei.gp.entity.UploadResult;
import com.zlei.gp.entity.User;
import com.zlei.gp.mapper.GoodsMapper;
import com.zlei.gp.mapper.UserMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.GoodsService;
import com.zlei.gp.utils.FileUtil;
import com.zlei.gp.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-23 15:00
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

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
    public CommonResult addGoods(String goodsName, String description, String price, MultipartFile picture, String userUuid) {

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
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, null, "图片上传成功");
    }

    @Override
    public CommonResult showGoods(String goodsName) {

        List<Goods> goodsList = null;

        if (goodsName == null) {
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
}
