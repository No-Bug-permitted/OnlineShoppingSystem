package org.example.logintest.control;

import jakarta.servlet.http.HttpServletRequest;
import org.example.logintest.pojo.Goods;
import org.example.logintest.pojo.Result;
import org.example.logintest.service.GoodsService;
import org.example.logintest.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/search")
    //public Result search(String keyword, HttpServletRequest request){
    public Result search(String keyword){

        List<Goods> goods= goodsService.searchGoodsByKeyword(keyword);

        return Result.success(goods);
    }
}
