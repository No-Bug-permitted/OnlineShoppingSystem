package org.example.logintest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.logintest.pojo.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("select * from goods where name like concat('%',#{keyword},'%')")
    List<Goods> searchGoodsByKeyword(String keyword);
}
