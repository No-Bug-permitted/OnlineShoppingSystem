package com.example.onlineshoppingsystem.dto;

public class CartItemDTO {
    private String skuId;    // 商品的 SKU ID
    private String selected; // 是否选中
    private int count;       // 商品的数量

    // Getter and Setter Methods
    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
