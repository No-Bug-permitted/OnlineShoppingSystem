package org.example.logintest.service;

import org.example.logintest.pojo.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> searchGoodsByKeyword(String keyword);
}
