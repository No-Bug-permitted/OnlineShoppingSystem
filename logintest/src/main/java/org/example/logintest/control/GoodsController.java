package org.example.logintest.control;

import org.example.logintest.pojo.Result;
import org.example.logintest.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/search")
    public Result search(String keyword){
        //检验token是否过期


        return Result.success(goodsService.searchGoodsByKeyword(keyword));
    }
}
