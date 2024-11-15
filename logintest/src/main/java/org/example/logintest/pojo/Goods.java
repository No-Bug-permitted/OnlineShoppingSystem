package org.example.logintest.pojo;

public class Goods {
    private String goods_id;
    private String shop_id;
    private String name;
    private Integer cateGory;
    private String goods_img;
    private String description;
    private String price;
    private String specifications;
    private Integer stock;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCateGory() {
        return cateGory;
    }

    public void setCateGory(Integer cateGory) {
        this.cateGory = cateGory;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

//    @Override
//    public String toString() {
//        return "Goods{" +
//                "goods_id='" + goods_id + '\'' +
//                ", shop_id='" + shop_id + '\'' +
//                ", name='" + name + '\'' +
//                ", cateGory=" + cateGory +
//                ", goods_img='" + goods_img + '\'' +
//                ", description='" + description + '\'' +
//                ", price='" + price + '\'' +
//                ", specifications='" + specifications + '\'' +
//                ", stock=" + stock +
//                '}';
//    }
@Override
public String toString() {
    return "{" +
            "id='" + goods_id + '\'' +
            ", name='" + name + '\'' +
            ", desc='" + description + '\'' +
            ", price='" + price + '\'' +
            ", picture='" + goods_img + '\'' +
            '}'+"\r\n";
}
}
