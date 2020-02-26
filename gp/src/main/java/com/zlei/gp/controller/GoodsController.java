package com.zlei.gp.controller;

import com.zlei.gp.response.CommonResult;
import com.zlei.gp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    // 处理文件上传
    @PostMapping(value = "/uploadFile")
    public String fileUpload (Map<String, Object> map, @RequestParam("file") MultipartFile file) {
        CommonResult commonResult = goodsService.uploadGoodsPicture(file);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/main/postGoods";
        }
        return "/main/postGoods";
    }

    @PostMapping("/addGoods")
    public String addGoods(Map<String, Object> map, String goodsName, String description, String price, MultipartFile picture, HttpSession session) {
        String userUuid = (String)session.getAttribute("userUuid");
        CommonResult commonResult = goodsService.addGoods(goodsName, description, price, picture, userUuid);
        if (!commonResult.isSuccess()) {
            map.put("msg", commonResult.getMsg());
            return "/main/postGoods";
        }
        return "redirect:/main.html";
    }

    @RequestMapping("/toAddGoods")
    public String toAddGoods() {
        return "/goods/postGoods";
    }

    @RequestMapping("/toUploadFile")
    public String toUploadFile() {
        return "/goods/uploadFile";
    }

}
