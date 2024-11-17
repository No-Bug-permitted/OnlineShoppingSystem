<template>
    <div class="person">
            <el-descriptions title="商户信息" style="font-size: 20px;">
            <el-descriptions-item label="商家名字" >JK大魔王</el-descriptions-item>
            <el-descriptions-item label="联系电话">18100000000</el-descriptions-item>
            <el-descriptions-item label="所上架商品数">1</el-descriptions-item>
            <el-descriptions-item label="地址标签">
              <el-tag size="small">学校</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="商铺地址">
              广州工业大学大学城校区三饭麦当劳
            </el-descriptions-item>
          </el-descriptions>
          </div>
</template>
<script>   
import { ElMessage } from 'element-plus';
import 'element-plus/theme-chalk/el-message.css';
</script>
<style>
.person{
    text-align: center;
    margin-top: 10%;
    margin-left: 25%;
    width: 50%;
  }
  .el-descriptions__body .el-descriptions__table:not(.is-bordered) .el-descriptions__cell{
    display: flex;
    font-size: 15px;
  }
  .el-descriptions__label:not(.is-bordered-label){
    width: 100px;
  }
  .el-descriptions__title{
    width: 150px;
    text-align: center ;
    margin-left: 80px;
    font-size: 20px;
  }
  </style>