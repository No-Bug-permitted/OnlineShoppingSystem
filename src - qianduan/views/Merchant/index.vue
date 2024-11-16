<script lang="ts" setup>
import {
Menu as IconMenu,
Setting,
List,
DataLine
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import LayoutNav from '../Layout/components/LayoutNav.vue';
import LayoutFooter from '../Layout/components/LayoutFooter.vue';

// 用于存储当前选中的菜单项
const selectedMenu = ref('2')  // 默认选中 'Navigator Two'

// 监听菜单项选择事件
const handleSelect = (index: string) => {
selectedMenu.value = index  // 更新选中的菜单项
}

//销售

</script>

<template>
    <LayoutNav />
    <el-row class="container">
      <!-- 左侧菜单栏 -->
      <el-col :span="6" class="menu">

        <div class="logo-container">
        <h1 class="logo">
          <RouterLink to="/" />
        </h1>
        <h2 class="mb-2">商家管理系统</h2>
      </div>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @select="handleSelect"  
        >
          <el-sub-menu index="1">
            <template #title>
                <el-icon><icon-menu /></el-icon>
              <span>商品管理</span>
            </template>
            <el-menu-item index="1-1">商品上架</el-menu-item>
            <el-menu-item index="1-2">商品下架</el-menu-item>
            <el-menu-item index="1-3">商品属性管理</el-menu-item>
            <el-menu-item index="1-4-1">库存管理</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon><List /></el-icon>
            <span>订单管理</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><setting /></el-icon>
            <span>个人信息</span>
          </el-menu-item>
          <el-menu-item index="5">
            <el-icon><DataLine /></el-icon>
            <span>销售信息分析</span>
          </el-menu-item>
        </el-menu>
      </el-col>
  
      <!-- 右侧内容区域 -->
      <el-col :span="18" class="content">
        <div v-if="selectedMenu === '1-1'">
          <h3>Content for item one</h3>
          <p>Details for item one...</p>
        </div>
        <div v-if="selectedMenu === '1-2'">
          <h3>Content for item two</h3>
          <p>Details for item two...</p>
        </div>
        <div v-if="selectedMenu === '1-3'">
          <h3>Content for item three</h3>
          <p>Details for item three...</p>
        </div>
        <div v-if="selectedMenu === '1-4-1'">
          <h3>Content for item four</h3>
          <p>Details for item four...</p>
        </div>
        <div v-if="selectedMenu === '2'">
          <h3>Content for Navigator Two</h3>
          <p>Details for Navigator Two...</p>
        </div>
        <div v-if="selectedMenu === '3'">
          <h3>Content for Navigator Three</h3>
          <p>Details for Navigator Three...</p>
        </div>
        <div v-if="selectedMenu === '4'">
          <div class="person">
            <el-descriptions title="User Info">
            <el-descriptions-item label="商家名字">JK大魔王</el-descriptions-item>
            <el-descriptions-item label="联系电话">18100000000</el-descriptions-item>
            <el-descriptions-item label="所上架商品数">1</el-descriptions-item>
            <el-descriptions-item label="Remarks">
              <el-tag size="small">School</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="Address">
              No.1188, Wuzhong Avenue, Wuzhong District, Suzhou, Jiangsu Province
            </el-descriptions-item>
          </el-descriptions>
          </div>
        </div>
        <div v-if="selectedMenu === '5'">
          <h3>Content for Navigator 呜呜呜</h3>
          <p>Details for Navigator 呜呜呜...</p>
        </div>
      </el-col>
    </el-row>
    <LayoutFooter />
  </template>
  
 
  
  <style scoped lang="scss">
  /* 容器样式，保持左右两边固定并居中 */
.container {
  display: flex;
  margin-top: 10px;
  justify-content: center;  /* 水平居中 */
  align-items: flex-start;   /* 左对齐 */
  height: 100vh;             /* 让父容器的高度充满整个视口 */
}

/* 固定宽度的左侧菜单 */
.menu {
  width: 250px; /* 左侧菜单固定宽度 */
  background-color: #f4f4f4;
  border-right: 1px solid #d2cfcf;
  
  padding: 10px;
  height: 100%;
  max-height: 900px;
}

/* 固定宽度的右侧内容区域 */
.content {
  width: calc(100% - 250px); /* 右侧内容占据剩余空间 */
  max-height: 900px;
  height: 100%;
  padding: 10px;
  background-color: #fff;
}

/* 让整个页面内的菜单、内容区宽度固定不变 */
.el-col {
  padding: 0;
  background-color: #fff;
}

.el-menu-vertical-demo {
  width: 100%;  /* 确保菜单栏宽度不会超过容器 */
}
.logo-container {
  display: flex;
  align-items: center;  /* 垂直居中对齐 */
  border-bottom: 1px solid #ddd;  /* 给父容器加下边框 */
  padding-bottom: 10px;  /* 给下边框增加一些内边距 */
}
.logo {
    display: inline-block;
    width: 100px;

    a {
      display: inline-block;
      height: 100px;
      width: 100%;
      background: url('@/assets/images/logo.png') no-repeat center 18px / contain;
    }
  }
  .mb-2{
    display: inline-block;
    height: 20px;
  }
  .person{
    width: 100%;
    align-items: center;
  }
  </style>
  