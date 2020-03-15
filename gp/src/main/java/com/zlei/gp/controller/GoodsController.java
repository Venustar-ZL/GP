package com.zlei.gp.controller;

import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.CommandMap;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ZLEI
 * @Title
 * @Description
 * @date 2020-02-23 14:56
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/toAddGoods")
    public String toAddGoods() {
        return "/goods/postGoods";
    }

    @RequestMapping("/toUploadFile")
    public String toUploadFile() { return "/goods/uploadFile"; }

    @RequestMapping("/toShowAllGoods")
    public String toShowAllGoods(Map<String, Object> map) {
        CommonResult commonResult = goodsService.showGoods(null);
        map.put("goodsList", commonResult.getData());
        return "/goods/showGoods"; }

    // 处理文件上传
    @PostMapping(value = "/uploadFile")
    public String fileUpload (Map<String, Object> map, @RequestParam("file") MultipartFile file) {
        CommonResult commonResult = goodsService.uploadGoodsPicture(file);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/goods/postGoods";
        }
        return "/goods/postGoods";
    }

    // 发布商品
    @PostMapping("/addGoods")
    public String addGoods(Map<String, Object> map, String goodsName, String description, String price, MultipartFile picture, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = goodsService.addGoods(goodsName, description, price, picture, userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/goods/postGoods";
        }
        return "redirect:/main.html";
    }

    // 显示全部商品
    @GetMapping("/showAllGoods")
    public String showGoods(Map<String, Object> map, @RequestParam(value = "goodsName", required = false) String goodsName) {
        CommonResult commonResult = goodsService.showGoods(goodsName);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/goods/showGoods";
        }
        map.put("goodsList", commonResult.getData());
        map.put("goodsName", goodsName);
        return "/goods/showGoods";
    }

    // 查看商品详情
    @GetMapping("/getGoodsInfo/{goodsUuid}")
    public String getGoodsInfo(@PathVariable("goodsUuid") String goodsUuid, Map<String, Object> map) {
        CommonResult commonResult = goodsService.getGoodsInfoById(goodsUuid);
        map.put("goods", commonResult.getData());
        return "/goods/view";
    }

    // 加入购物车
    @GetMapping("/addShopCar/{goodsUuid}")
    public String addShopCar(@PathVariable("goodsUuid") String goodsUuid, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUUid");
        goodsService.addShopCar(goodsUuid, userUuid);
        return "/goods/showGoods";
    }

    // 查看购物车
    @GetMapping("/toShopCar")
    public String toShopCar(Map<String, Object> map, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUUid");
        CommonResult commonResult = goodsService.getShopCar(userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/goods/shopCar";
        }
        map.put("goodsList", commonResult.getData());
        return "/goods/showGoods";
    }


}
