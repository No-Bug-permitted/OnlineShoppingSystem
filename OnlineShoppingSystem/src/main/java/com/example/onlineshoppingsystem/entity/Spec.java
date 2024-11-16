package com.example.onlineshoppingsystem.entity;
import java.util.List;
public class Spec {
    private String name;
    private List<SpecValue> values;

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SpecValue> getValues() {
        return values;
    }

    public void setValues(List<SpecValue> values) {
        this.values = values;
    }
}
