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
                        "https://img12.360buyimg.com/n1/jfs/t1/189699/4/52806/137891/673713fdF70464e57/79702edd8560befd.jpg.avif",
                        "https://img12.360buyimg.com/n1/jfs/t1/191241/24/53837/163016/673713fcF3668d856/ba0d99cc9faa2c1a.jpg.avif",
                        "https://img12.360buyimg.com/n1/jfs/t1/225744/5/25461/68970/66fad379F2d7ba810/4cd8eb1659a10bf5.jpg.avif"
                )),
                entry("specs", List.of(
                        Map.of(
                                "name", "功能",
                                "values", List.of(
                                        Map.of("name", "镇店综合", "picture", "https://img12.360buyimg.com/n9/s40x40_jfs/t1/189699/4/52806/137891/673713fdF70464e57/79702edd8560befd.jpg.avif"),
                                        Map.of("name", "淡化黑眼圈", "picture", "https://img12.360buyimg.com/n9/s40x40_jfs/t1/188824/31/52051/175340/67371207F6b3a1ef4/de83624196a73829.jpg.avif")
                                )
                        ),
                        Map.of(
                                "name", "规格",
                                "values", List.of(
                                        Map.of("name", "15ml", "picture", "https://t15.baidu.com/it/u=3846800817,2248368687&fm=224&app=112&f=JPEG?w=500&h=500")
                                )
                        )
                )),
                entry("skus", List.of(
                        Map.of(
                                "id", "300248014",
                                "skuCode", "300248014",
                                "price", "199.00",
                                "oldPrice", "199.00",
                                "inventory", 4912,
                                "specs", List.of(
                                        Map.of("name", "功能", "valueName", "镇店综合"),
                                        Map.of("name", "规格", "valueName", "15ml")
                                )
                        ),
                        Map.of(
                                "id", "300248015",  // 新的 id
                                "skuCode", "300248015",  // 新的 skuCode
                                "price", "199.00",
                                "oldPrice", "199.00",
                                "inventory", 4912,
                                "specs", List.of(
                                        Map.of("name", "功能", "valueName", "淡化黑眼圈"),
                                        Map.of("name", "规格", "valueName", "15ml")
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
                                "http://img30.360buyimg.com/popWareDetail/jfs/t1/218267/16/36568/137267/653a0e3dF360328a7/40986553c34034a4.jpg",
                                "http://img30.360buyimg.com/popWareDetail/jfs/t1/192176/1/41401/182786/653a0e49F652b348e/685febbba501ca21.jpg"
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
