package com.example.onlineshoppingsystem.controller;

import com.example.onlineshoppingsystem.entity.Banner;
import com.example.onlineshoppingsystem.entity.NewItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.onlineshoppingsystem.entity.Category;
import com.example.onlineshoppingsystem.entity.Goods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/home")
public class HomeController {

    @GetMapping("/banner")
    public ResponseEntity<Object> getBanner(@RequestParam String distributionSite) {
        // 根据请求的 distributionSite 判断返回的轮播图内容
        List<Banner> banners = getBannersByDistributionSite(distributionSite);

        // 构造返回的数据
        BannerResponse response = new BannerResponse();
        response.setCode("1");
        response.setMsg("操作成功");
        response.setResult(banners);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<Object> getNewItems() {
        // 获取新鲜好物数据
        List<NewItem> newItems = getNewItemsList();

        // 构造返回的数据
        NewItemResponse response = new NewItemResponse();
        response.setCode("1");
        response.setMsg("操作成功");
        response.setResult(newItems);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 根据 distributionSite 返回不同的轮播图
    private List<Banner> getBannersByDistributionSite(String distributionSite) {
        List<Banner> banners = new ArrayList<>();

        // 模拟从数据库或者其他地方获取轮播图数据
        if ("1".equals(distributionSite)) {
            banners.add(createBanner("1", "https://b.appsimg.com/upload/momin/2024/11/13/91/1731478133611.jpg", "1"));
            banners.add(createBanner("2", "https://b.appsimg.com/upload/momin/2024/11/15/158/1731655817619.jpg", "1"));
            banners.add(createBanner("3", "https://kaola-haitao.oss.kaolacdn.com/3462928e-8788-4476-a636-77d1a805b0b9.jpg?x-oss-process=image/resize,w_1920/quality,q_90", "1"));
        }

        return banners;
    }

    // 模拟新鲜好物数据
    private List<NewItem> getNewItemsList() {
        List<NewItem> newItems = new ArrayList<>();

        newItems.add(createNewItem("4008506", "MonkeyKing护眼霜", "66666666.00", "https://img12.360buyimg.com/n1/jfs/t1/189699/4/52806/137891/673713fdF70464e57/79702edd8560befd.jpg.avif"));
        newItems.add(createNewItem("3996270", "超级大冰箱", "444449.00", "https://imgcps.jd.com/ling-cubic/turing/deliverysoa/6fdbf6243d70b5cc719926bae94e3ef7/cr/s/q70.jpg"));
        newItems.add(createNewItem("4008506", "Herbal Essences洗发水", "666.00", "https://imgcps.jd.com/img-cubic/creative_server_cia_jdcloud/v2/2020219/100016285326/FocusFullshop/CkNqZnMvdDEvMjMxNDk2LzI4LzI1MTQyLzYzMjYwLzY2Yzc3MjMyRjMxZDc2ZjgyLzI0ZDUwM2MyZmE3NzdlNzkucG5nEgU1MDUtdzABOPume0IYChLkuqzpgInlpb3nianmsYfogZoQARgBQhMKD-eJqee-juS7t-abtOS8mBACQhAKDOeri-WNs-aKoui0rRAGQgoKBuS8mOi0qBAHWI7Nvcv0Ag/cr/s/q.jpg"));
        newItems.add(createNewItem("4008506", "爱华仕行李箱", "9999.00", "https://img20.360buyimg.com/babel/s1180x940_jfs/t20271115/160232/29/49875/46337/6736a1aaF1205f315/bf0701196a647057.jpg.avif"));
        return newItems;
    }

    @GetMapping("/category/head")
    public ResponseEntity<Object> getCategoryHead() {
        // 构造分类数据和商品推荐
        List<Category> categories = getCategoriesWithGoods();

        // 构造返回的数据
        NewItemResponse.CategoryResponse response = new NewItemResponse.CategoryResponse();
        response.setCode("1");
        response.setMsg("操作成功");
        response.setResult(categories);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 获取分类和推荐商品
    private List<Category> getCategoriesWithGoods() {
        List<Category> categories = new ArrayList<>();

        // 模拟分类数据
        Category category = new Category();
        category.setId("1005000");
        category.setName("居家");
        category.setPicture("http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-05-06/201516e3-25d0-48f5-bcee-7f0cafb14176.png");

        // 模拟子分类数据
        List<Category> children = new ArrayList<>();
        children.add(createChildCategory("1005999003", "居家生活用品", "http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-22/7f6a7b20-7902-4b43-b9c5-f33151ef1334.jpg?quality=95&imageView"));
        children.add(createChildCategory("1008017", "收纳", "https://yanxuan.nosdn.127.net/366989e4d730594e86fcd60b5ab19acc.png?quality=95&imageView"));
        children.add(createChildCategory("1017000", "宠物食品", "https://yanxuan.nosdn.127.net/b42a85ef15f856081ea9f49e5f6893e2.png?quality=95&imageView"));
        children.add(createChildCategory("109243003", "艺术藏品", "https://yanxuan.nosdn.127.net/9544b81aaa14c26a8038c2365ff3c2bc.png?quality=95&imageView"));

        category.setChildren(children);

        // 模拟推荐商品数据
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(createGoods("3529022", "中派沙发", "比zjk还软", "13.90", "https://img14.360buyimg.com/jdcms/s480x480_jfs/t1/185458/16/51325/165553/67340e62F63a6483b/6532759f3a05b51e.jpg.avif"));
        goodsList.add(createGoods("3506005", "高洁丝安睡裤", "安睡一整夜", "328.00", "https://img11.360buyimg.com/jdcms/s480x480_jfs/t1/233795/11/17220/129109/66441dc4F780c1f0b/1759ee5911df278c.jpg.avif"));
        goodsList.add(createGoods("3499024", "zjk精华霜", "zjk独家秘制", "158.00", "https://img30.360buyimg.com/jdcms/s480x480_jfs/t1/205701/38/49030/133902/67334c00Ff8964d00/9ff524c8750726f6.jpg.avif"));

        category.setGoods(goodsList);

        categories.add(category);
        return categories;
    }

    // 用于创建子分类对象
    private Category createChildCategory(String id, String name, String picture) {
        Category child = new Category();
        child.setId(id);
        child.setName(name);
        child.setPicture(picture);
        child.setChildren(null);
        child.setGoods(null);
        return child;
    }

    // 用于创建商品对象
    private Goods createGoods(String id, String name, String desc, String price, String picture) {
        Goods goods = new Goods();
        goods.setId(id);
        goods.setName(name);
        goods.setDesc(desc);
        goods.setPrice(price);
        goods.setPicture(picture);
        return goods;
    }

    // 用于创建 Banner 对象
    private Banner createBanner(String id, String imgUrl, String alt) {
        Banner banner = new Banner();
        banner.setId(id);
        banner.setImgUrl(imgUrl);
        banner.setAlt(alt);
        return banner;
    }

    // 用于创建 NewItem 对象
    private NewItem createNewItem(String id, String name, String price, String picture) {
        NewItem newItem = new NewItem();
        newItem.setId(id);
        newItem.setName(name);
        newItem.setPrice(price);
        newItem.setPicture(picture);
        return newItem;
    }
}

// Banner 响应体
class BannerResponse {
    private String code;
    private String msg;
    private List<Banner> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Banner> getResult() {
        return result;
    }

    public void setResult(List<Banner> result) {
        this.result = result;
    }
}

// 新鲜好物 响应体
class NewItemResponse {
    private String code;
    private String msg;
    private List<NewItem> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewItem> getResult() {
        return result;
    }

    public void setResult(List<NewItem> result) {
        this.result = result;
    }

    // 全部分类响应体
    static class CategoryResponse {
        private String code;
        private String msg;
        private List<Category> result;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<Category> getResult() {
            return result;
        }

        public void setResult(List<Category> result) {
            this.result = result;
        }
    }
}
