<script setup>
import LayoutNav from './components/LayoutNav.vue';
import LayoutHeader from './components/LayoutHeader.vue';
import LayoutFooter from './components/LayoutFooter.vue';
import LayoutFixed from './components/LayoutFixed.vue';
import { onMounted ,ref} from 'vue';
import { useCategoryStore } from '@/stores/categoryStore.ts';
import '../gonggao.scss';
import {
 Bell
} from '@element-plus/icons-vue'
const categoryStore = useCategoryStore();

onMounted(() => categoryStore.getCategory());


</script>

<template>
  
  <LayoutFixed />
  <LayoutNav />
  <LayoutHeader />
  <div class="gonggao">
    <el-icon class="tubiao"><Bell /></el-icon>
  <div class="notice">
    
    <div class="notice__inner">
        <span class="notice__item notice__item-first">网上购物，看准商家！小心骗子！！就算被骗找我们组长也行</span>
        <span class="notice__item notice__item-second">临近双12，本网站将在11月31日开启促销活动！</span>
    </div>
</div>
  </div>
  
  <RouterView />
  <LayoutFooter />
</template>

<style scoped>
.gonggao{
  width: 1240px;
  margin: 0 auto;
}
.tubiao {
  position: absolute;
  font-size: 30px;
  display: inline-block; /* 将图标显示为内联块元素，允许它与文本在一行 */
  vertical-align: middle; /* 确保图标和文本垂直居中 */
  margin-top: 7px;
  margin-left: 10px; /* 可选，控制图标和文本之间的间距 */
}
.notice{
    width: 1190px;
    height: 40px;
    border-radius: 8px;
    border: 1px solid #f0ebeb;
    overflow: hidden;
    margin-left: 50px;
}
.notice__inner{
  display: flex; /* 使用 flex 布局，使得图标和文本在同一行 */
  align-items: center; /* 垂直居中对齐 */
    height: 100%;
    font-size: 20px;
    color: #333;
    line-height: 40px;
    white-space: nowrap;
    position: relative;
}
.notice__item{
    position: absolute;
    top: 0;
    left: 100%;
    height: 100%;
}
.notice__item-first{
    padding-right: 80%;
    animation: rollFirst 25s linear infinite;
}
.notice__item-second{
    padding-right: 80%;
    animation: rollSecond 25s linear 15s infinite;
}
@keyframes rollFirst {
    0% {
        transform: translateX(0);
    }
    100% {
        transform: translateX(-200%);
    }
}
@keyframes rollSecond {
    0% {
        transform: translateX(0);
    }
    100% {
        transform: translateX(-200%);
    }
}
</style>
