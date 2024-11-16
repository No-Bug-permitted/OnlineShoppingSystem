package com.example.onlineshoppingsystem.entity;

public class NewItem {
    private String id;
    private String name;
    private String price;
    private String picture;

    // 构造函数
    public NewItem() {}

    public NewItem(String id, String name, String price, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    // 可选：重写toString()方法，方便打印对象时输出内容
    @Override
    public String toString() {
        return "NewItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
