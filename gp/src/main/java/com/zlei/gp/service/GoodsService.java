package com.zlei.gp.service;

import com.zlei.gp.response.CommonResult;
import org.springframework.web.multipart.MultipartFile;

public interface GoodsService {

    public CommonResult uploadGoodsPicture(MultipartFile file);

    public CommonResult addGoods(String goodsName, String description, String price, MultipartFile picture, String userUuid);

    public CommonResult showGoods(String goodsName);

    public CommonResult getGoodsInfoById(String goodsUuid);

}
