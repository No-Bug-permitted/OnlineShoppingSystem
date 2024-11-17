package com.example.onlineshoppingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/search")
public class SearchController {

    @PostMapping("/goods")
    public ResponseEntity<Map<String, Object>> searchGoods(@RequestBody Map<String, Object> request) {
        // 获取搜索关键词
        String keyword = (String) request.get("keyword");

        // 模拟商品搜索结果
        if (keyword != null && keyword.contains("护眼霜")) {
            Map<String, Object> product = new HashMap<>();
            product.put("id", "333666");
            product.put("name", "MonkeyKing护眼霜");
            product.put("desc", "可以保护好monkeyking的火眼金睛");
            product.put("price", "66666666");
            product.put("picture", "https://img12.360buyimg.com/n1/jfs/t1/189699/4/52806/137891/673713fdF70464e57/79702edd8560befd.jpg.avif");

            // 构造返回结果
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("code", "1");
            response.put("msg", "操作成功");
            response.put("result", List.of(product));

            return ResponseEntity.ok(response);
        } else {
            // 构造空结果
            Map<String, Object> response = new HashMap<>();
            response.put("code", "1");
            response.put("msg", "未找到相关商品");
            response.put("result", List.of());

            return ResponseEntity.ok(response);
        }
    }
}
