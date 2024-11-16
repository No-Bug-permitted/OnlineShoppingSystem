package com.example.onlineshoppingsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@RestController
@RequestMapping("/api/user/goods")
public class GoodsController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getGoodsDetail(@RequestParam("id") String id) {
        // 构造复杂响应数据
        Map<String, Object> result = Map.ofEntries(
                entry("id", "333666"),
                entry("name", "monkeyking的护眼霜"),
                entry("spuCode", "333666"),
                entry("desc", "可以保护好monkeyking的火眼金睛"),
                entry("price", "66666666"),
                entry("oldPrice", "99999999"),
                entry("brand", "JK牌"),
                entry("salesCount", "666"),
                entry("commentCount", "5"),
                entry("collectCount", "9999999"),
                entry("mainPictures", List.of(
                        "https://yanxuan-item.nosdn.127.net/21d2e9b754487aa3030ce105493a9473.jpg",
                        "https://yanxuan-item.nosdn.127.net/adb0345dc57c2589671f41de805d2105.jpg"
                )),
                entry("specs", List.of(
                        Map.of(
                                "name", "花型",
                                "values", List.of(
                                        Map.of("name", "花与熊", "picture", "https://yanxuan-item.nosdn.127.net/ec3b277074be06a76cfea97fb6f0becc.png")
                                )
                        ),
                        Map.of(
                                "name", "尺码",
                                "values", List.of(
                                        Map.of("name", "90cm", "picture", "https://example.com/default.png")
                                )
                        )
                )),
                entry("skus", List.of(
                        Map.of(
                                "id", "300248014",
                                "skuCode", "300248014",
                                "price", "199.00",
                                "oldPrice", "199.00",
                                "specs", List.of(
                                        Map.of("name", "花型", "valueName", "花与熊"),
                                        Map.of("name", "尺码", "valueName", "90ml")
                                )
                        )
                )),
                entry("categories", List.of(
                        Map.of(
                                "id", "100115555",
                                "name", "护肤品",
                                "layer", 2,
                                "parent", Map.of(
                                        "id", "1001155",
                                        "name", "母婴",
                                        "layer", 1,
                                        "parent", " "
                                )
                        ),
                        Map.of(
                                "id", "1001155",
                                "name", "母婴",
                                "layer", 1,
                                "parent", " "
                        )
                )),
                entry("details", Map.of(
                        "pictures", List.of(
                                "https://yanxuan-item.nosdn.127.net/example1.jpg",
                                "https://yanxuan-item.nosdn.127.net/example2.jpg"
                        )
                ))
        );

        Map<String, Object> response = Map.of(
                "code", "1",
                "msg", "操作成功",
                "result", result
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
