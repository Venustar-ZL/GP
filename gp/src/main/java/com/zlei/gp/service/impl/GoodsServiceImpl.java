package com.zlei.gp.service.impl;

import com.zlei.gp.mapper.GoodsMapper;
import com.zlei.gp.response.CommonResult;
import com.zlei.gp.response.ConstantEnum;
import com.zlei.gp.service.GoodsService;
import com.zlei.gp.utils.FileUtil;
import com.zlei.gp.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        String fileName = picture.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UuidUtil.getUUID() + suffixName;
        boolean msg = FileUtil.uploadToLinux(picture);
        if (msg == false) {
            return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_FALL_CUSTOM, null, "文件上传失败");
        }
        String goodsUuid = UuidUtil.getUUID();
        goodsMapper.addGoods(goodsName, description, price, fileName, goodsUuid, userUuid);
        return CommonResult.buildWithDatAndMessage(ConstantEnum.GLOBAL_SUCCESS, null, "图片上传成功");
    }
}
