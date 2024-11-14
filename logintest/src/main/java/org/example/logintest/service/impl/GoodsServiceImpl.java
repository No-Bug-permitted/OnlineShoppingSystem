package org.example.logintest.service.impl;

import org.example.logintest.mapper.GoodsMapper;
import org.example.logintest.pojo.Goods;
import org.example.logintest.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> searchGoodsByKeyword(String keyword) {
        return goodsMapper.searchGoodsByKeyword(keyword);
    }

}
